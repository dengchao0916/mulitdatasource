package com.example.multidatasource.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author : dengchao
 * @date : 2020 08 02
 */
@Slf4j
public class MultiDataSourceRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        ConstructorArgumentValues cav = new ConstructorArgumentValues();
        cav.addGenericArgumentValue(registry);
        MutablePropertyValues mpv = new MutablePropertyValues();

        RootBeanDefinition beanDefinition = new RootBeanDefinition(RoutingStrategyCreator.class,cav,mpv);
        registry.registerBeanDefinition("annotationMultiDataSourceCreator", beanDefinition);
    }
}
