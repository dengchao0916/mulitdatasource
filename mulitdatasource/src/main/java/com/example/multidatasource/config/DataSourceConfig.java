package com.example.multidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.multidatasource.dynamicdatasource.MultiDataSource;
import com.example.multidatasource.support.DruidProperties;
import com.example.multidatasource.support.DsRoutingSetProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册多数据源组件
 * @author : dengchao
 */
@Configuration
@EnableConfigurationProperties(value = {DruidProperties.class, DsRoutingSetProperties.class})
public class DataSourceConfig {

    @Autowired
    private DruidProperties druidProperties;

    @Autowired
    private DsRoutingSetProperties dsRoutingSetProperties;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid0")
    public DataSource dataSource0() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(druidProperties.getDruid0username());
        dataSource.setPassword(druidProperties.getDruid0password());
        dataSource.setUrl(druidProperties.getDruid0jdbcUrl());
        dataSource.setDriverClassName(druidProperties.getDruid0driverClass());
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid1")
    public DataSource dataSource1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(druidProperties.getDruid1username());
        dataSource.setPassword(druidProperties.getDruid1password());
        dataSource.setUrl(druidProperties.getDruid1jdbcUrl());
        dataSource.setDriverClassName(druidProperties.getDruid1driverClass());
        return dataSource;
    }

    @Bean("multiDataSource")
    public MultiDataSource dataSource() {
        MultiDataSource dataSource = new MultiDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("datasource0", dataSource0());
        targetDataSources.put("datasource1", dataSource1());

        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSource0());


        Map<Integer, String> setMappings = new HashMap<>();
        setMappings.put(0, "datasource0");
        setMappings.put(1, "datasource1");

        dsRoutingSetProperties.setDataSourceKeyMapping(setMappings);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("multiDataSource") MultiDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:/com/example/multidatasource/mapper/UserMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("multiDataSource") MultiDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);

    }
}
