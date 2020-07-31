package com.example.multidatasource.annotation;

/**
 * @author : dengchao
 * @date : 2020 07 31
 */

import com.example.multidatasource.constant.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Router {

    String routingField() default Constant.DEFAULT_ROUTING_FIELD;
}
