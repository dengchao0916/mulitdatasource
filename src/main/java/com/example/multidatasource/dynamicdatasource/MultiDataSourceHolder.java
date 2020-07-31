package com.example.multidatasource.dynamicdatasource;

/**
 * 多数据源key 缓存类
 *
 * @author : dengchao
 */
public class MultiDataSourceHolder {
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> tableHolder = new ThreadLocal<>();

    /**
     * 保存数据源的key
     *
     * @param key
     */
    public static void setDataSourceKey(String key) {
        dataSourceHolder.set(key);
    }

    /**
     * 取出数据源的key
     * @return
     */
    public static String getDataSourceKey() {
        return dataSourceHolder.get();
    }

    /**
     * 清除key
     */
    public static void clearDataSourceKey(){
        dataSourceHolder.remove();
    }

    /**
     * 保存数据源的key
     *
     * @param key
     */
    public static void setTableKey(String key) {
        tableHolder.set(key);
    }

    /**
     * 取出数据源的key
     * @return
     */
    public static String getTableKey() {
        return tableHolder.get();
    }

    /**
     * 清除key
     */
    public static void clearTableKey(){
        tableHolder.remove();
    }
}
