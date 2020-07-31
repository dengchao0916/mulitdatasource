package com.example.multidatasource.entity;

import com.example.multidatasource.dynamicdatasource.MultiDataSourceHolder;

/**
 * @author : dengchao
 * @date : 2020 07 31
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
