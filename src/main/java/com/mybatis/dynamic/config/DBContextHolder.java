package com.mybatis.dynamic.config;

import com.mybatis.dynamic.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 切換資料庫來源
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:06 PM
 */
@Slf4j
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<DBTypeEnum>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        remove();
        log.info("使用的資料庫：  MASTER ...");
        set(DBTypeEnum.MASTER);
    }

    /**
     * 如果有多台slave可切換，default 1台slave
     */
    public static void slave() {
        remove();
        log.info("使用的資料庫：  SLAVE ....");
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE);
        } else {
            set(DBTypeEnum.SLAVE);
        }
    }

    /**
     * remove 線程
     */
    public static void remove(){
        contextHolder.remove();
    }
}
