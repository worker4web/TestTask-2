package ua.com.worker4web.uth.minigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by worker4web on 11/14/2017.
 */

@Configuration
@ComponentScan("ua.com.worker4web.uth.minigateway")
@EntityScan("ua.com.worker4web.uth.minigateway.model")
@PropertySource("classpath:config.properties")
@EnableTransactionManagement

public class AppConfig extends WebMvcConfigurerAdapter {

    @Value("${hibernate.dialect}")
    private String sqlDialect;

    @Value("${hibernate.show_sql}")
    private Boolean showSql;

    @Value("${hbm2ddl.auto}")
    private String hbm2dllAuto;

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2dllAuto);
        return properties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(showSql);
        adapter.setDatabasePlatform(sqlDialect);

        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
            (DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }
}
