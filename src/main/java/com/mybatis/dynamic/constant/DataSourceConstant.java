package com.mybatis.dynamic.constant;

/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:11 PM
 */
public interface DataSourceConstant {
    interface Master {
        String JDBC_VALUE = "spring.dynamic.datasource.master";

        String DATA_SOURCE = "dataSourceMaster";
    }

    interface Slave {
        String JDBC_VALUE = "spring.dynamic.datasource.slave";

        String DATA_SOURCE = "dataSourceSlave";
    }

    interface ResourceMapper {
        String CLASSPATH_PATH = "classpath:mapper/*.xml";
    }

}
