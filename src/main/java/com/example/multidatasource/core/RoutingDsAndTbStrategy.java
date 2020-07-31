package com.example.multidatasource.core;

import com.example.multidatasource.dynamicdatasource.MultiDataSourceHolder;

/**
 * 多库多表
 *
 * @author : dengchao
 */
public class RoutingDsAndTbStrategy extends AbstractRouting {
    @Override
    public String getDataSourceKey(String routingField) {

        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        Integer dsIndex = routingFieldHashCode % getDsRoutingSetProperties().getDataSourceNum();

        String dataSourceKey = getDsRoutingSetProperties().getDataSourceKeyMapping().get(dsIndex);

        MultiDataSourceHolder.setDataSourceKey(dataSourceKey);

        System.out.println("根据路由字段 : " + getDsRoutingSetProperties().getRoutingField() + ",值 : "
                + routingFieldHashCode + ",计算出数据库索引 : " + dsIndex + ",数据源key的值 : " + dataSourceKey);

        return dataSourceKey;
    }

    @Override
    public String getTableKey(String routingField) {

        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        Integer tbIndex = routingFieldHashCode % getDsRoutingSetProperties().getTableNum();

        String tableSuffix = getFormatTableSuffix(tbIndex);

        MultiDataSourceHolder.setTableKey(tableSuffix);

        return tableSuffix;
    }
}
