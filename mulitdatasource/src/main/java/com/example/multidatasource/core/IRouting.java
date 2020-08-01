package com.example.multidatasource.core;

/**
 * 路由接口
 * @author : dengchao
 */
public interface IRouting {

    /**
     * 计算出数据库下标
     * @param routingField
     * @return
     */
     String calDataSourceKey(String routingField);

    /**
     * 计算routingField字段的hashcode值
     * @param routingField
     * @return
     */
     Integer getRoutingFieldHashCode(String routingField);

    /**
     * 计算一个库所在表的索引值
     * @param routingField
     * @return
     */
     String calTableKey(String routingField);

    /**
     * 计算出表的后缀
     * @param tableIndex
     * @return
     */
     String getFormatTableSuffix(Integer tableIndex);


}
