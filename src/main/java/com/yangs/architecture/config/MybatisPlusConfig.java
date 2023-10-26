package com.yangs.architecture.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sol
 * @date 2023/10/23 17:52
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.yangs.architecture.dao")//basePackages:接口文件的包路径
public class MybatisPlusConfig {
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Bean(name = "YangsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.yangs")//我们配置文件中的前缀
    public DataSource getYangsDataSource() {

        return DataSourceBuilder.create().build();
    }
    @Bean(name = "TestDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")//我们配置文件中的前缀
    public DataSource getTestDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "dynamicDataSource")
    public DynamicRouting DataSource(@Qualifier("YangsDataSource") DataSource yangsDataSource,
                                     @Qualifier("TestDataSource") DataSource testDataSource) {
        //配置多数据源
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("YangsDataSource",yangsDataSource);
        targetDataSource.put("TestDataSource",testDataSource);
        DynamicRouting dataSource = new DynamicRouting();
        //多数据源
        dataSource.setTargetDataSources(targetDataSource);
        //默认数据源
        dataSource.setDefaultTargetDataSource(yangsDataSource);
        return dataSource;
    }


    @Bean("SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        //关闭驼峰
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(false);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
}