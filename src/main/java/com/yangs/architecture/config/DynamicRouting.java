package com.yangs.architecture.config;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author sol
 * @date 2023/10/25 14:32
 * @Version 1.0
 */
public class DynamicRouting extends AbstractRoutingDataSource {
    @Override
    public Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
