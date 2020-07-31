package com.example.multidatasource.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : dengchao
 * @date : 2020 07 31
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
@Component
public class DruidProperties {
    private String druid0username;
    private String druid0password;
    private String druid0jdbcUrl;
    private String druid0driverClass;

    private String druid1username;
    private String druid1password;
    private String druid1jdbcUrl;
    private String druid1driverClass;
}
