package shala.ezoo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import shala.ezoo.aspect.LoggingAspect;
import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.dao.HibernateAnimalDAOImpl;
import shala.ezoo.dao.HibernateFeedingScheduleDAOImpl;
import shala.ezoo.logging.DatabaseLogger;
import shala.ezoo.logging.LoggerDefault;
import shala.ezoo.model.Animal;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class Config {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
//      ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/eZoo");
        ds.setUsername("postgres");
        ds.setPassword("***REMOVED***");
        
        return ds;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(ds);
        factoryBean.setPackagesToScan(new String[] {"shala.ezoo.model"});
        
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
        
        
        factoryBean.setHibernateProperties(props);
        
        return factoryBean;
    }
    
    @Bean
    public FeedingScheduleDAO feedingScheduleRepository(SessionFactory sessionFactory) {
        HibernateFeedingScheduleDAOImpl repo = new HibernateFeedingScheduleDAOImpl();
        repo.setSessionFactory(sessionFactory);
        
        return repo;
    }
//    
//    @Bean
//    public Animal animal() {
//        return new Animal();
//    }
    
   
    @Bean
    public AnimalDAO animalDAO(SessionFactory sessionFactory) {
        HibernateAnimalDAOImpl repo = new HibernateAnimalDAOImpl();
        repo.setSessionFactory(sessionFactory);
        return repo;
    }
    
    @Bean
    public DatabaseLogger databaseLogger() {
        return new LoggerDefault();
    }
    
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
    
    @Bean
    public PlatformTransactionManager txManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
        
    }
    
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
