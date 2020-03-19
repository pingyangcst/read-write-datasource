package com.github.xjs.readwrite.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ReadWriteDataSourceHolder {

    /*
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<DataSourceTypeEnum> CONTEXT_HOLDER = new  ThreadLocal<DataSourceTypeEnum>();

    private static final AtomicInteger counter = new AtomicInteger(0);

    /**
     * @Description: 设置数据源的变量
     * @param dateSourceType
     * @return void
     * @throws
     */
    public static void setCurrentDateSource(DataSourceTypeEnum dateSourceType){
        CONTEXT_HOLDER.set(dateSourceType);
    }

    /**
     * @Description: 获得数据源的变量
     * @return String
     * @throws
     */
    public static DataSourceTypeEnum getCurrentDateSource(){
        return CONTEXT_HOLDER.get();
    }

    /**
     * @Description: 清空所有的数据源变量
     * @return void
     * @throws
     */
    public static void clearCurrentDateSource(){
        CONTEXT_HOLDER.remove();
    }

    public static void switchToMaster() {
        setCurrentDateSource(DataSourceTypeEnum.MASTER);
        log.info("切换到master");
    }

    public static void switchToSlave() {
        //  轮询
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(0);
        }
        if (index == 0) {
            setCurrentDateSource(DataSourceTypeEnum.SLAVE1);
            log.info("切换到slave1");
        }else {
            setCurrentDateSource(DataSourceTypeEnum.SLAVE2);
            log.info("切换到slave2");
        }
    }

}
