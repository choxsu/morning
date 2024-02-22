package com.syc.common.utils.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * 获取代理原始对象的工具
 * @author xq.su
 */
@Slf4j
public class AopTargetUtils {

    /**
     * 获取代理对象的原始对象
     *
     * @author fengshuonan
     * @date 2020/10/19 16:21
     */
    public static Object getTarget(Object proxy) {

        // 不是代理对象，直接返回参数对象
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }

        // 判断是否是jdk还是cglib代理的对象
        try {
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                return getJdkDynamicProxyTargetObject(proxy);
            } else {
                return getCglibProxyTargetObject(proxy);
            }
        } catch (Exception e) {
            log.error("获取代理对象异常", e);
            return null;
        }
    }

    /**
     * 获取cglib代理的对象
     *
     * @author fengshuonan
     * @date 2020/10/19 16:21
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }

    /**
     * 获取jdk代理的对象
     *
     * @author fengshuonan
     * @date 2020/10/19 16:22
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }

}  