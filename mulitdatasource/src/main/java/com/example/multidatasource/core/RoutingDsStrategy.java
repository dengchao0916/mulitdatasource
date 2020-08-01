package com.example.multidatasource.core;

import com.example.multidatasource.dynamicdatasource.MultiDataSourceHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 多库一表
 *
 * @author : dengchao
 */
@Slf4j
public class RoutingDsStrategy extends AbstractRouting {
    @Override
    public String calDataSourceKey(String routingField) {
        Integer routingFieldHashCode = getRoutingFieldHashCode(routingField);

        Integer dsIndex = routingFieldHashCode % getDsRoutingSetProperties().getDataSourceNum();

        String dataSourceKey = getDsRoutingSetProperties().getDataSourceKeyMapping().get(dsIndex);

        MultiDataSourceHolder.setDataSourceKey(dataSourceKey);

        log.info("根据路由字段:{},值:{},计算出数据库索引:{},数据源key的值:{}", getDsRoutingSetProperties().getRoutingField(), routingFieldHashCode, dsIndex, dataSourceKey);

        return dataSourceKey;
    }

    @Override
    public String calTableKey(String routingField) {
        MultiDataSourceHolder.setTableKey("");
        log.info("根据路由字段:{},表的后缀为:{}", getDsRoutingSetProperties().getRoutingField(), "");

        return "";
    }
}
