package com.example.multidatasource.config;

import com.example.multidatasource.core.IRouting;
import com.example.multidatasource.core.RoutingDsAndTbStrategy;
import com.example.multidatasource.core.RoutingDsStrategy;
import com.example.multidatasource.core.RoutingTbStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : dengchao
 * @date : 2020 07 31
 */
@Configuration
public class RoutingStrategyConfig {


    @Bean
    @ConditionalOnProperty(prefix = "dsroutingset",name = "routingStrategy",havingValue = "ROUTING_DS_TABLE_STRATEGY")
    public IRouting routingDsAndTbStrategy(){
        return new RoutingDsAndTbStrategy();
    }

    @Bean
    @ConditionalOnProperty(prefix = "dsroutingset",name = "routingStrategy",havingValue = "ROUTING_DS_STRATEGY")
    public IRouting routingDsStrategy(){
        return new RoutingDsStrategy();
    }

    @Bean
    @ConditionalOnProperty(prefix = "dsroutingset",name = "routingStrategy",havingValue = "ROUTING_TABLE_STRATEGY")
    public IRouting routingTbStrategy(){
        return new RoutingTbStrategy();
    }
}
