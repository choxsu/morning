package com.syc.modules.admin.biz.plugin.dupsubmit.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.syc.framework.security.util.JwtUtils;
import com.syc.modules.common.exception.BusinessException;
import com.syc.modules.common.result.ResultCode;
import com.syc.modules.admin.biz.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;

/**
 * 处理重复提交的切面
 *
 * @author xq.su
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class DuplicateSubmitAspect {

    private final RedissonClient redissonClient;
    private static final String RESUBMIT_LOCK_PREFIX = "LOCK:RESUBMIT:";

    /**
     * 防重复提交切点
     */
    @Pointcut("@annotation(preventDuplicateSubmit)")
    public void preventDuplicateSubmitPointCut(PreventDuplicateSubmit preventDuplicateSubmit) {
        log.info("定义防重复提交切点");
    }

    @Around("preventDuplicateSubmitPointCut(preventDuplicateSubmit)")
    public Object doAround(ProceedingJoinPoint pjp, PreventDuplicateSubmit preventDuplicateSubmit) throws Throwable {

        String resubmitLockKey = generateResubmitLockKey();
        if (resubmitLockKey != null) {
            // 防重提交锁过期时间
            int expire = preventDuplicateSubmit.expire();
            RLock lock = redissonClient.getLock(resubmitLockKey);
            // 获取锁失败，直接返回 false
            boolean lockResult = lock.tryLock(0, expire, TimeUnit.SECONDS);
            if (!lockResult) {
                // 抛出重复提交提示信息
                throw new BusinessException(ResultCode.REPEAT_SUBMIT_ERROR);
            }
        }
        return pjp.proceed();
    }


    /**
     * 获取重复提交锁的 key
     */
    private String generateResubmitLockKey() {
        String resubmitLockKey = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isNotBlank(token)) {
            String jti = Convert.toStr(JwtUtils.parseToken(token).get("jti"), null);
            resubmitLockKey = RESUBMIT_LOCK_PREFIX + jti + ":" + request.getMethod() + "-" + request.getRequestURI();
        }
        return resubmitLockKey;
    }

}
