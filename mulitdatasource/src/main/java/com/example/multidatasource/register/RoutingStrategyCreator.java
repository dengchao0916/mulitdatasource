package com.example.multidatasource.register;

import com.example.multidatasource.constant.Constant;
import com.example.multidatasource.core.RoutingDsAndTbStrategy;
import com.example.multidatasource.core.RoutingDsStrategy;
import com.example.multidatasource.core.RoutingTbStrategy;
import com.example.multidatasource.support.DsRoutingSetProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author : dengchao
 * @date : 2020 08 02
 */
@Slf4j
@Data
public class RoutingStrategyCreator implements BeanPostProcessor {

    private BeanDefinitionRegistry registry;

    private static final String DS_ROUTING_SET_PROPERTIES = "dsRoutingSetProperties";

    public RoutingStrategyCreator(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals(DS_ROUTING_SET_PROPERTIES)) {
            log.info("beanName:{}", beanName);
            DsRoutingSetProperties properties = (DsRoutingSetProperties) bean;
            String strategy = properties.getRoutingStrategy();
            RootBeanDefinition definition;
            switch (strategy) {
                case Constant.ROUTING_DS_TABLE_STRATEGY:
                    definition = new RootBeanDefinition(RoutingDsAndTbStrategy.class);
                    registry.registerBeanDefinition("routingDsAndTbStrategy", definition);
                    break;
                case Constant.ROUTING_DS_STRATEGY:
                    definition = new RootBeanDefinition(RoutingDsStrategy.class);
                    registry.registerBeanDefinition("routingDsStrategy", definition);
                    break;
                case Constant.ROUTING_TABLE_STRATEGY:
                    definition = new RootBeanDefinition(RoutingTbStrategy.class);
                    registry.registerBeanDefinition("routingTbStrategy", definition);
                    break;
            }
        }
        return bean;
    }
}
