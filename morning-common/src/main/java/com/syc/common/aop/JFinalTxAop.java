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
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.SimpleTransactionStatus;

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
     * 自定义JFinal 事物注解
     * value中的意思解释
     *
     * @annotation 表示注解只能支持方法上
     * @within 表示注解在类下面所有的方法 ， 暂时不使用这种方式
     */
    //@Pointcut("@annotation(com.syc.common.aop.JFinalTx)")
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    private void method() {}

    @Around(value = "method()", argNames = "pjp")
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
            conn.commit();
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
}