package com.example.multisource.config;

import com.example.multisource.util.CustomSqlSessionTemplate;
import com.github.pagehelper.PageHelper;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan(basePackages ="com.example.multisource.neo.mapper",sqlSessionTemplateRef ="SqlSessionTemplate")
public class DataSourceConfig {

    @Bean("properties1")
    @ConfigurationProperties(prefix = "spring.datasource.db")
    public DataSourceProperties dataSourceProperties1()
    {
        return new DataSourceProperties();
    }

    @Bean("properties2")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSourceProperties dataSourceProperties2()
    {
        return new DataSourceProperties();
    }

    @Bean("properties3")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSourceProperties dataSourceProperties3()
    {
        return new DataSourceProperties();
    }


    @Bean(name = "ds")
    public AtomikosDataSourceBean dataSource1() {
        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        DataSourceProperties dataSourceProperties=dataSourceProperties1();
        atomikosDataSourceBean.setUniqueResourceName("ds");
        atomikosDataSourceBean.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
        Properties properties = new Properties();
        properties.put("URL", dataSourceProperties.getUrl());
        properties.put("user", dataSourceProperties.getUsername());
        properties.put("password", dataSourceProperties.getPassword());
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }


    @Bean(name = "ds1")
    public AtomikosDataSourceBean dataSource2() {
        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        DataSourceProperties dataSourceProperties=dataSourceProperties2();
        atomikosDataSourceBean.setUniqueResourceName("db1");
        atomikosDataSourceBean.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
        Properties properties = new Properties();
        properties.put("URL", dataSourceProperties.getUrl());
        properties.put("user", dataSourceProperties.getUsername());
        properties.put("password", dataSourceProperties.getPassword());
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    @Bean(name = "ds2")
    public AtomikosDataSourceBean dataSource3() {
        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        DataSourceProperties dataSourceProperties=dataSourceProperties3();
        atomikosDataSourceBean.setUniqueResourceName("ds2");
        atomikosDataSourceBean.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
        Properties properties = new Properties();
        properties.put("URL", dataSourceProperties.getUrl());
        properties.put("user", dataSourceProperties.getUsername());
        properties.put("password", dataSourceProperties.getPassword());
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    /*@Bean(name = "ds1")
    @ConfigurationProperties(prefix = "spring.datasource.db1") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "ds2")
    @ConfigurationProperties(prefix = "spring.datasource.db2") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }*/

    @Bean(name = "dynamicDS1")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("ds", dataSource1());
        dsMap.put("ds1", dataSource2());
        dsMap.put("ds2", dataSource3());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Bean(name = "pageHelper")
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }


    @Bean("sqlSession1")
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean  sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource1());
        factoryBean.setPlugins(new Interceptor[]{pageHelper()});
        return factoryBean ;
    }

    @Bean("sqlSession2")
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean  sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource2());
        factoryBean.setPlugins(new Interceptor[]{pageHelper()});
        return factoryBean ;
    }

    @Bean("sqlSession3")
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean  sqlSessionFactory3() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource3());
        factoryBean.setPlugins(new Interceptor[]{pageHelper()});
        return factoryBean ;
    }

    @Bean("SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception
    {
        CustomSqlSessionTemplate sqlSessionTemplate=new CustomSqlSessionTemplate(sqlSessionFactory1().getObject());
        Map<Object,SqlSessionFactory> sqlSessionFactoryMap=new HashMap<>();
        sqlSessionFactoryMap.put("ds",sqlSessionFactory1().getObject());
        sqlSessionFactoryMap.put("ds1",sqlSessionFactory2().getObject());
        sqlSessionFactoryMap.put("ds2",sqlSessionFactory3().getObject());
        sqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        return sqlSessionTemplate;
    }

    /*@Bean("SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory().getObject()); // 使用上面配置的Factory
        return template;
    }*/

}
