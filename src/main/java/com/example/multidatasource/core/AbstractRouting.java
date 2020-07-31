package com.example.multidatasource.core;

import com.example.multidatasource.constant.Constant;
import com.example.multidatasource.support.DsRoutingSetProperties;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author : dengchao
 * @date : 2020 07 31
 */
@EnableConfigurationProperties(value = {DsRoutingSetProperties.class})
@Data
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

    private void checkRoutingDsTableStrategyConfig() {
        if (dsRoutingSetProperties.getTableNum() <= 1 || dsRoutingSetProperties.getDataSourceNum() <= 1) {
            System.out.println("配置策略为多库多表，数据库个数必须大于1，每一个库中的表个数也必须大于1");
        }
    }

    private void checkRoutingDsStrategyConfig() {
        if (dsRoutingSetProperties.getTableNum() != 1 || dsRoutingSetProperties.getDataSourceNum() <= 1) {
            System.out.println("配置策略为多库一表，数据库个数必须大于1，每一个库中的表个数必须等于1");
        }
    }

    private void checkRoutingTableStrategyConfig() {
        if (dsRoutingSetProperties.getTableNum() <= 1 || dsRoutingSetProperties.getDataSourceNum() != 1) {
            System.out.println("配置策略为一库多表，数据库个数必须等于1，每一个库中的表个数也必须大于1");
        }
    }




}
