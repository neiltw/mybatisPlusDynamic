package com.mybatis.dynamic.handler;

import com.mybatis.dynamic.config.DBContextHolder;
import com.mybatis.dynamic.enums.DBTypeEnum;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Properties;

/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:15 PM
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class , Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class , Object.class , RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class , Object.class , RowBounds.class, ResultHandler.class }),

})
public class MybatisPluginInterceptor implements Interceptor {


    /**
     * 補仃 線程是一致性的，當第一次執行為select 無法切換成master , 透過攔截器自動切換
     * @param invocation
     * @return Object
     */
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        boolean synchronizationActive =  TransactionSynchronizationManager.isSynchronizationActive();
        if(!synchronizationActive){
            if( DBContextHolder.get().equals(DBTypeEnum.SLAVE) && ( ms.getSqlCommandType().equals(SqlCommandType.DELETE) || ms.getSqlCommandType().equals(SqlCommandType.UPDATE) || ms.getSqlCommandType().equals(SqlCommandType.INSERT) ) ){
                DBContextHolder.master();
            }
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}
