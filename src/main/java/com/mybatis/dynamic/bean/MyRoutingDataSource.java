package com.mybatis.dynamic.bean;

import com.mybatis.dynamic.config.DBContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:05 PM
 */
@Slf4j
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("線程[{}]，切换到的資料庫為:{}", Thread.currentThread().getId(), DBContextHolder.get());
        return DBContextHolder.get();
    }
}
