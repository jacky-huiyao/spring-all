package com.hy.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.hy.mybatis.dao.slave",sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    // static final String PACKAGE = "com.mxf.dao.master";
    // static final String MAPPER_LOCATION = "classpath:mapper/slave/*.xml";

    // 获取数据连接池
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties("mybatis.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 开启事务管理，需要数据源
    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(slaveDataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("slaveDataSource") DataSource slaveDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(slaveDataSource);
        // sessionFactory.setMapperLocations(new
        // PathMatchingResourcePatternResolver()
        // .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage("com.hy.mybatis.domain");
        return sessionFactory.getObject();
    }
    
}
