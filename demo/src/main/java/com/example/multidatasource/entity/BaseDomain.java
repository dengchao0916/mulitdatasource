package com.example.multidatasource.entity;

import com.example.multidatasource.dynamicdatasource.MultiDataSourceHolder;

/**
 * @author : dengchao
 */
public class BaseDomain {
    private String tableSuffix;

    public String getTableSuffix() {
         this.tableSuffix = MultiDataSourceHolder.getTableKey();
        return tableSuffix;
    }

    public void setTableSuffix(String tableSuffix) {
        this.tableSuffix = tableSuffix;
    }
}
