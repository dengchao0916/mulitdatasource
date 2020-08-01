package com.example.multidatasource.core;

import com.example.multidatasource.dynamicdatasource.MultiDataSourceHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 一库多表
 * @author : dengchao
 */
@Slf4j
public class RoutingTbStrategy  extends AbstractRouting{
    @Override
    public String calDataSourceKey(String routingField) {
        MultiDataSourceHolder.setDataSourceKey("datasource0");
        log.info("根据路由字段:{},数据源key的值:{}", getDsRoutingSetProperties().getRoutingField(),  "datasource0");

        return "datasource0";
    }

    @Override
    public String calTableKey(String routingField) {
        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        Integer tbIndex = routingFieldHashCode % getDsRoutingSetProperties().getTableNum();

        String tableSuffix = getFormatTableSuffix(tbIndex);

        MultiDataSourceHolder.setTableKey(tableSuffix);

        log.info("根据路由字段:{},值:{},计算出表索引:{},表的后缀为:{}", getDsRoutingSetProperties().getRoutingField(), routingFieldHashCode, tbIndex, tableSuffix);

        return tableSuffix;
    }
}
