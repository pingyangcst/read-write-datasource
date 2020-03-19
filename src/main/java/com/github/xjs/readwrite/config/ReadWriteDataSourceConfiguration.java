package com.github.xjs.readwrite.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.LinkedHashMap;

/**
 * 关于数据源配置，参考SpringBoot官方文档第79章《Data Access》
 * 79. Data Access
 * 79.1 Configure a Custom DataSource
 * 79.2 Configure Two DataSources
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class ReadWriteDataSourceConfiguration implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Primary
    @Bean(name = "dataSource")
    public ReadWriteDataSource dataSource() {
        ReadWriteDataSource readWriteDataSource = new ReadWriteDataSource();
        LinkedHashMap<Object, Object> dataSources = new LinkedHashMap<>(3);
        DataSource masterDataSource = masterDataSource();
        dataSources.put(DataSourceTypeEnum.MASTER, masterDataSource);
        dataSources.put(DataSourceTypeEnum.SLAVE1, slave1DataSource());
        dataSources.put(DataSourceTypeEnum.SLAVE2, slave2DataSource());
        readWriteDataSource.setDefaultTargetDataSource(masterDataSource);
        readWriteDataSource.setTargetDataSources(dataSources);
        readWriteDataSource.afterPropertiesSet();
        return readWriteDataSource;
    }

    private DataSource masterDataSource(){
        return createDataSource(DataSourceTypeEnum.MASTER.getName());
    }

    private DataSource slave1DataSource() {
        return createDataSource(DataSourceTypeEnum.SLAVE1.getName());
    }

    private DataSource slave2DataSource() {
        return createDataSource(DataSourceTypeEnum.SLAVE2.getName());
    }

    private DataSource createDataSource(String name) {
        DataSource ds = Binder.get(environment).bind("spring.datasource.hikari", Bindable.of(HikariDataSource.class)).get();
        HikariDataSource hikariDataSource = (HikariDataSource)ds;
        hikariDataSource.setPoolName("datasource["+name+"]");
        hikariDataSource.setDriverClassName(environment.getProperty("spring.datasource."+name+".driver-class-name"));
        hikariDataSource.setJdbcUrl(environment.getProperty("spring.datasource."+name+".jdbc-url"));
        hikariDataSource.setUsername(environment.getProperty("spring.datasource."+name+".username"));
        hikariDataSource.setPassword(environment.getProperty("spring.datasource."+name+".password"));
        return ds;
    }

}
