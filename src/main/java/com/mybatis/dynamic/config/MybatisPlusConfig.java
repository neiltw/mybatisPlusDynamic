package com.mybatis.dynamic.config;

import com.mybatis.dynamic.constant.DataSourceConstant;
import com.mybatis.dynamic.handler.MybatisPluginInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:14 PM
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Resource(name = "myRoutingDataSource")
    private DataSource myRoutingDataSource;

    @Resource
    private MybatisPluginInterceptor mybatisPluginInterceptor;

    /**
     * @return sqlSessionFactory
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConstant.ResourceMapper.CLASSPATH_PATH));

        sqlSessionFactoryBean.setPlugins(mybatisPluginInterceptor );
//        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
//        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        return sqlSessionFactoryBean.getObject();
    }


    /**
     * 事務配置
     * @return 事務管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(myRoutingDataSource);
        return tx;
    }
}
