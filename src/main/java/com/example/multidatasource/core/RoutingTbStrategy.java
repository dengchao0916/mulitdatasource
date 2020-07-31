package com.example.multidatasource.core;

/**
 * 一库多表
 * @author : dengchao
 */
public class RoutingTbStrategy  extends AbstractRouting{
    @Override
    public String getDataSourceKey(String routingField) {
        return null;
    }

    @Override
    public String getTableKey(String routingField) {
        return null;
    }
}
