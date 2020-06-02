package spring.intro.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import spring.intro.model.User;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {
        "spring.intro.dao",
        "spring.intro.service"
})
public class ApplicationConfig {

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_intro?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setAnnotatedClasses(User.class);
        return localSessionFactoryBean;
    }
}
