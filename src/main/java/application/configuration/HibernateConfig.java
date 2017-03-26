package application.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ud on 19/3/17.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan({"application.configuration"})
@PropertySource(value = {"classpath:application.properties"}) //Sets property to environment variable of spring
public class HibernateConfig {

    @Autowired
    private Environment environment;

    /*
    Method to return a session factory object
    */
    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"application.model"});
        sessionFactory.setHibernateProperties(hiberProperties());
        return sessionFactory;
    }

    /*
    Method to return a datasource object // a db conection
    */
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return driverManagerDataSource;
    }

    private Properties hiberProperties(){
        Properties properties=new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    /**
     *
     * takes a session factory object created above and
     * returns a transactionManager object to handle all transaction
     *
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(s);
        return transactionManager;
    }
}
