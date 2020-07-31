package com.example.multidatasource.aop;

import com.example.multidatasource.annotation.Router;
import com.example.multidatasource.core.IRouting;
import com.example.multidatasource.dynamicdatasource.MultiDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : dengchao
 */
@Aspect
@Component
@Slf4j
public class RoutingAspect {

    @Autowired
    private IRouting routing;

    @Pointcut("@annotation(com.example.multidatasource.annotation.Router)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //获取方法调用名称
        Method method = getInvokeMethod(joinPoint);
        //获取指定的注解
        Router router = method.getAnnotation(Router.class);
        //获取指定的路由key
        String routingField = router.routingField();
        //获取方法入参
        Object[] args = joinPoint.getArgs();

        boolean havingRoutingField = false;

        if (args != null && args.length > 0) {
            for (int index = 0; index < args.length; index++) {
                String routingFieldValue = BeanUtils.getProperty(args[index], routingField);
                if (!StringUtils.isEmpty(routingFieldValue)) {
                    String dbKey = routing.getDataSourceKey(routingFieldValue);
                    String tbKey = routing.getTableKey(routingFieldValue);
                    log.info("选择的数据库是{},选择的表是{}",dbKey,tbKey);
                    havingRoutingField = true;
                    break;
                } else {

                }
            }

            if (!havingRoutingField) {
                log.error("入参中没有包含路由字段");
            }
        }
    }

    private Method getInvokeMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method;
    }


    @After("pointcut()")
    public void after() {
        MultiDataSourceHolder.clearDataSourceKey();
        MultiDataSourceHolder.clearTableKey();
    }
}
