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
@MapperScan(basePackages = "com.hy.mybatis.dao.master",sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    // static final String PACKAGE = "com.mxf.dao.master";
    // static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    // 获取数据连接池
    @Bean(name = "masterDataSource")
    @ConfigurationProperties("mybatis.master")
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 开启事务管理，需要数据源
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        // sessionFactory.setMapperLocations(new
        // PathMatchingResourcePatternResolver()
        // .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage("com.hy.mybatis.domain");

        return sessionFactory.getObject();
    }
    
}
