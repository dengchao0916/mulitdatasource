package com.example.multidatasource.annotation;

import com.example.multidatasource.config.DataSourceConfig;
import com.example.multidatasource.config.RoutingStrategyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : dengchao
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({DataSourceConfig.class, RoutingStrategyConfig.class})
public @interface EnableMultiDataSource {
}
