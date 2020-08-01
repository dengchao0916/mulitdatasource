package com.example.multidatasource.core;

import com.example.multidatasource.constant.Constant;
import com.example.multidatasource.support.DsRoutingSetProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 路由规则抽象类
 * @author : dengchao
 */
@EnableConfigurationProperties(value = {DsRoutingSetProperties.class})
@Data
@Slf4j
public abstract class AbstractRouting implements IRouting, InitializingBean {

    @Autowired
    private DsRoutingSetProperties dsRoutingSetProperties;


    @Override
    public Integer getRoutingFieldHashCode(String routingField) {
        return Math.abs(routingField.hashCode());
    }

    /**
     * 获取表的后缀
     *
     * @param tableIndex
     * @return
     */
    @Override
    public String getFormatTableSuffix(Integer tableIndex) {
        StringBuffer stringBuffer = null;
        try {
            stringBuffer = new StringBuffer(dsRoutingSetProperties.getTableSuffixConnect());
            stringBuffer.append(String.format(getDsRoutingSetProperties().getTableSuffixStyle(), tableIndex));
        } catch (Exception e) {
            System.out.println("格式化异常");
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 工程启动是，检查配置路由参数和策略是否匹配
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        switch (getDsRoutingSetProperties().getRoutingStrategy()) {
            case Constant
                    .ROUTING_DS_TABLE_STRATEGY:
                checkRoutingDsTableStrategyConfig();
                break;
            case Constant.ROUTING_DS_STRATEGY:
                checkRoutingDsStrategyConfig();
                break;
            case Constant.ROUTING_TABLE_STRATEGY:
                checkRoutingTableStrategyConfig();
                break;
        }
    }

    private void checkRoutingDsTableStrategyConfig() throws Exception {
        if (dsRoutingSetProperties.getTableNum() <= 1 || dsRoutingSetProperties.getDataSourceNum() <= 1) {
            log.error("配置策略为多库多表，数据库个数必须大于1，每一个库中的表个数也必须大于1");
            throw new Exception("多库多表配置不匹配");
        }
    }

    private void checkRoutingDsStrategyConfig() throws Exception {
        if (dsRoutingSetProperties.getTableNum() != 1 || dsRoutingSetProperties.getDataSourceNum() <= 1) {
            log.error("配置策略为多库一表，数据库个数必须大于1，每一个库中的表个数必须等于1");
            throw new Exception("多库一表配置不匹配");
        }
    }

    private void checkRoutingTableStrategyConfig() throws Exception {
        if (dsRoutingSetProperties.getTableNum() <= 1 || dsRoutingSetProperties.getDataSourceNum() != 1) {
            log.error("配置策略为一库多表，数据库个数必须等于1，每一个库中的表个数也必须大于1");
            throw new Exception("一库多表配置不匹配");
        }
    }


}
