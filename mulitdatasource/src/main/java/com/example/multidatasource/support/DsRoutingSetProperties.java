package com.example.multidatasource.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 插件配置类
 * @author : dengchao
 */

@ConfigurationProperties(prefix = "dsroutingset")
@Data
@Component
public class DsRoutingSetProperties {

    /**
     * 默认1个数据库
     */
    private Integer dataSourceNum = 1;

    /**
     * 每个库对应表个数 默认1个
     */
    private Integer tableNum = 1;

    private String routingField;

    private String tableSuffixStyle;

    private String tableSuffixConnect;

    private String routingStrategy;

    private Map<Integer, String> dataSourceKeyMapping;

}
