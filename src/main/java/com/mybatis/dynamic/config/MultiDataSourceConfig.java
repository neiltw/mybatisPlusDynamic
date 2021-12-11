package com.mybatis.dynamic.config;

import com.mybatis.dynamic.bean.MyRoutingDataSource;
import com.mybatis.dynamic.constant.DataSourceConstant;
import com.mybatis.dynamic.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:12 PM
 */
@Configuration
@Slf4j
public class MultiDataSourceConfig {

    /**
     * DataSource
     *
     * @return Master DataSource
     */
    @Bean(name = DataSourceConstant.Master.DATA_SOURCE)
    @ConfigurationProperties(prefix = DataSourceConstant.Master.JDBC_VALUE)
    @Primary
    public DataSource dataSourceMaster() {
        return DataSourceBuilder.create().build();
    }

    /**
     * DataSource
     *
     * @return Slave1 DataSource
     */
    @Bean(name = DataSourceConstant.Slave.DATA_SOURCE)
    @ConfigurationProperties(prefix = DataSourceConstant.Slave.JDBC_VALUE)
    public DataSource dataSourceSlave() {
        return DataSourceBuilder.create().build();
    }


    /**
     * default master
     * @param masterDataSource 主要資料庫
     * @param slaveDataSource 備用資料庫
     * @return DataSource
     */
    @Bean
    public DataSource myRoutingDataSource(@Qualifier(DataSourceConstant.Master.DATA_SOURCE) DataSource masterDataSource,
                                          @Qualifier(DataSourceConstant.Slave.DATA_SOURCE) DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE, slaveDataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);//設置預設為master
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }

}
