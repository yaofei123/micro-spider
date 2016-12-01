package com.yaofei.framework.datasource.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static java.util.Collections.singletonList;

/**
 * Created by fei.yao on 16/7/26.
 */

@Configuration
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String dbHost;

    @Value("${spring.data.mongodb.port}")
    private String dbPort;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${spring.data.mongodb.username}")
    private String userName;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Override
    protected String getDatabaseName() {
        return this.dbName;
    }

    public MongoConfig() {
        if (null == dbPort || "".equalsIgnoreCase(dbPort.trim())) {
            dbPort = "27017";
        }
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    @Bean(name = "mongoDatasource")
    public Mongo mongo() throws Exception {
        return new MongoClient(singletonList(new ServerAddress(dbHost, Integer.valueOf(dbPort))),
                singletonList(MongoCredential.createCredential(userName, dbName, password.toCharArray())));
    }
}
