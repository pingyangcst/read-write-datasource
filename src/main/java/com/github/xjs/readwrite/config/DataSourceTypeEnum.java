package com.github.xjs.readwrite.config;

public enum DataSourceTypeEnum {


    MASTER("master"),
    SLAVE1("slave1"),
    SLAVE2("slave2");

    /**
     * 这里的name要跟配置文件中的spring.datasource.XXX对应起来
     * */
    private String name;

    private DataSourceTypeEnum(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
