package com.example.multidatasource.core;

import com.example.multidatasource.dynamicdatasource.MultiDataSourceHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 多库多表
 *
 * @author : dengchao
 */
@Slf4j
public class RoutingDsAndTbStrategy extends AbstractRouting {
    @Override
    public String getDataSourceKey(String routingField) {

        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        Integer dsIndex = routingFieldHashCode % getDsRoutingSetProperties().getDataSourceNum();

        String dataSourceKey = getDsRoutingSetProperties().getDataSourceKeyMapping().get(dsIndex);

        MultiDataSourceHolder.setDataSourceKey(dataSourceKey);

        log.info("根据路由字段:{},值:{},计算出数据库索引:{},数据源key的值:{}", getDsRoutingSetProperties().getRoutingField(), routingFieldHashCode, dsIndex, dataSourceKey);

        return dataSourceKey;
    }

    @Override
    public String getTableKey(String routingField) {

        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        Integer tbIndex = routingFieldHashCode % getDsRoutingSetProperties().getTableNum();

        String tableSuffix = getFormatTableSuffix(tbIndex);

        MultiDataSourceHolder.setTableKey(tableSuffix);

        log.info("根据路由字段:{},值:{},计算出数据库索引:{},表的后缀为:{}", getDsRoutingSetProperties().getRoutingField(), routingFieldHashCode, tbIndex, tableSuffix);

        return tableSuffix;
    }
}
