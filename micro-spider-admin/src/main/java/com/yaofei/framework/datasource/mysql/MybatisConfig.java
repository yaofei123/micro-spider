package com.yaofei.framework.datasource.mysql;

import com.yaofei.framework.util.MybatisMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by fei.yao on 16/7/26.
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter
@MapperScan(basePackages = "com.yaofei", markerInterface = MybatisMapper.class)
public class MybatisConfig implements EnvironmentAware {

    private final Logger LOGGER = LoggerFactory.getLogger(MybatisConfig.class);
    private final DataSource datasource;


    private RelaxedPropertyResolver mybatisPropertyResolver;

    /**
     * 获取配置信息
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        mybatisPropertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }

    @Autowired
    public MybatisConfig(@Qualifier("mysqlDatasource") DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        //添加mybatis配置文件
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        bean.setConfigLocation(resourceResolver.getResource(mybatisPropertyResolver.getProperty("config-location")));
        try {
            bean.setMapperLocations(resourceResolver.getResources(mybatisPropertyResolver.getProperty("mapper-locations")));
            return bean.getObject();
        } catch (IOException e) {
            LOGGER.error("获取mapper资源出现异常",e);
            throw new RuntimeException("获取mapper资源出现异常",e);
        } catch (Exception e){
            LOGGER.error("初始化sqlSessionFactory时出现异常",e);
            throw new RuntimeException("初始化sqlSessionFactory时出现异常",e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
