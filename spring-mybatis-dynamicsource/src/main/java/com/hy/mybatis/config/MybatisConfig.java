package com.hy.mybatis.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@MapperScan("com.hy.mybatis.dao")
public class MybatisConfig {

    @Autowired
    private MybatisProperties mybatisProperties;

    @Bean(name = "master")
    @ConfigurationProperties("mybatis.mysql.master")
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties("mybatis.mysql.slave")
    @Primary
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /*
     * 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
         //配置多数据源
        final Map<Object, Object> dbMap = new HashMap<>();
        dbMap.put("master",masterDataSource());
        dbMap.put("slave",slaveDataSource());
        dynamicDataSource.setTargetDataSources(dbMap);

        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        return dynamicDataSource;
    }

     /*
      * 数据库连接会话工厂
      * 将动态数据源赋给工厂
      */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource){
         SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
         sqlSessionFactory.setDataSource(dataSource);
         sqlSessionFactory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
         sqlSessionFactory.setConfiguration(mybatisProperties.getConfiguration());
//         sqlSessionFactory.setMapperLocations(mybatisProperties.resolveMapperLocations());
         return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
