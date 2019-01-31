package com.syc.common.aop;

import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.NestedTransactionHelpException;
import com.jfinal.plugin.activerecord.tx.TxConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author choxsu
 * @date 2018/4/13
 */
@Aspect
@Component
public class JFinalTxAop {
    /**
     * 是否可以手动提交事物，默认可以自动提交
     */
    private static boolean canCommit = true;
    /**
     * 自定义JFinal 事物注解:类上面
     *
     * @within 表示注解在类下面所有的方法
     */
    @Pointcut("@within(org.springframework.transaction.annotation.Transactional)")
    private void methodWithin() {
    }

    /**
     * 自定义JFinal 事物注解:方法上面
     *
     * @annotation 表示注解只能支持方法上
     */
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    private void methodAnno() {
    }

    /**
     * 兼容@Transactional可以放在类上和方法上
     * 当放类上时，类中所有方法都支持事物注解，
     * 如果类上没有@Transactional，然而是放在方法上的，那么只有此方法支持事物注解
     *
     * @param pjp 切入点目标对象
     * @return 返回切入方法的返回数据
     * @throws Throwable
     */
    @Around(value = "methodWithin() || methodAnno()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        Config config = getConfigWithTxConfig(pjp);
        if (config == null)
            config = DbKit.getConfig();

        Connection conn = config.getThreadLocalConnection();
        // Nested transaction support
        if (conn != null) {
            try {
                if (conn.getTransactionIsolation() < getTransactionLevel(config))
                    conn.setTransactionIsolation(getTransactionLevel(config));
                retVal = pjp.proceed();
                return retVal;
            } catch (SQLException e) {
                throw new ActiveRecordException(e);
            }
        }
        Boolean autoCommit = null;
        try {
            conn = config.getConnection();
            autoCommit = conn.getAutoCommit();
            config.setThreadLocalConnection(conn);
            conn.setTransactionIsolation(getTransactionLevel(config));// conn.setTransactionIsolation(transactionLevel);
            conn.setAutoCommit(false);
            retVal = pjp.proceed();
            if (canCommit) {
                conn.commit();
            } else {
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    LogKit.error(e1.getMessage(), e1);
                }
            }
        } catch (NestedTransactionHelpException e) {
            if (conn != null) try {
                conn.rollback();
            } catch (Exception e1) {
                LogKit.error(e1.getMessage(), e1);
            }
            LogKit.logNothing(e);
        } catch (Throwable t) {
            if (conn != null) try {
                conn.rollback();
            } catch (Exception e1) {
                LogKit.error(e1.getMessage(), e1);
            }
            throw t instanceof RuntimeException ? (RuntimeException) t : new ActiveRecordException(t);
        } finally {
            canCommit = true;
            try {
                if (conn != null) {
                    if (autoCommit != null)
                        conn.setAutoCommit(autoCommit);
                    conn.close();
                }
            } catch (Throwable t) {
                // can not throw exception here, otherwise the more important exception in previous catch block can not be thrown
                LogKit.error(t.getMessage(), t);
            } finally {
                // prevent memory leak
                config.removeThreadLocalConnection();
            }
        }
        return retVal;
    }

    /**
     * 获取配置的事务级别
     *
     * @param config
     * @return
     */
    protected int getTransactionLevel(Config config) {
        return config.getTransactionLevel();
    }

    /**
     * @param pjp
     * @return Config
     */
    public static Config getConfigWithTxConfig(ProceedingJoinPoint pjp) {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        TxConfig txConfig = method.getAnnotation(TxConfig.class);
        if (txConfig == null)
            txConfig = pjp.getTarget().getClass().getAnnotation(TxConfig.class);

        if (txConfig != null) {
            Config config = DbKit.getConfig(txConfig.value());
            if (config == null)
                throw new RuntimeException("Config not found with TxConfig: " + txConfig.value());
            return config;
        }
        return null;
    }

    public static boolean setRollbackOnly() {
        canCommit = false;
        try {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (NoTransactionException e) {
            return false;
        }
        return true;
    }
}