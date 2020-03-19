package com.github.xjs.readwrite.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ReadWriteDataSource extends AbstractRoutingDataSource {

    /**
     * 获得数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return ReadWriteDataSourceHolder.getCurrentDateSource();
    }

}
