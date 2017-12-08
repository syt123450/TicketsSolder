package com.ticketSolder.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan(basePackages = H2Config.PACKAGE, sqlSessionFactoryRef = "h2SqlSessionFactory")
@PropertySource("classpath:database.properties")
public class H2Config {

    static final String PACKAGE = "com.ticketSolder.model.dao.h2";

    @Value("${h2.datasource.url}")
    private String url;

    @Value("${h2.datasource.username}")
    private String user;

    @Value("${h2.datasource.password}")
    private String password;

    @Value("${h2.datasource.driverClassName}")
    private String driverClass;

    @Value("${h2.mybatis.mapperLocations}")
    private String mapperLocation;

    @Value("${h2.mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Value("${h2.initSql.source}")
    private String initSqlSource;

    @Bean(name = "h2DataSource")
    public DataSource h2DataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setTestWhileIdle(false);
        try {
            URL url = Resources.getResource(initSqlSource);
            String text = Resources.toString(url, Charsets.UTF_8);
            List<String> initSql = new ArrayList<>();
            ScriptUtils.splitSqlScript(text, ";", initSql);
            dataSource.setConnectionInitSqls(initSql);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    @Bean(name = "h2TransactionManager")
    public DataSourceTransactionManager h2TransactionManager() {
        return new DataSourceTransactionManager(h2DataSource());
    }

    @Bean(name = "h2SqlSessionFactory")
    public SqlSessionFactory h2SqlSessionFactory(@Qualifier("h2DataSource") DataSource h2DataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(h2DataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        return sessionFactory.getObject();
    }
}