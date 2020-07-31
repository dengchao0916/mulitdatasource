package com.example.multidatasource.core;

/**
 * 多库一表
 * @author : dengchao
 */
public class RoutingDsStrategy  extends AbstractRouting{
    @Override
    public String getDataSourceKey(String routingField) {
        return null;
    }

    @Override
    public String getTableKey(String routingField) {
        return null;
    }
}
