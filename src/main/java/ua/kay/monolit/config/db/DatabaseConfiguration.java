package ua.kay.monolit.config.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ua.kay.monolit.repository")
@EntityScan(basePackages = "ua.kay.monolit.model")
public class DatabaseConfiguration {
    @Value("${c3p0.driver}") private String DRIVER;
    @Value("${c3p0.password}") private String PASSWORD;
    @Value("${c3p0.url}") private String URL;
    @Value("${c3p0.username}") private String USERNAME;
    @Value("${c3p0.maxPoolSize}") private int MAX_POOL_SIZE;
    @Value("${c3p0.minPoolSize}") private int MIN_POOL_SIZE;
    @Value("${c3p0.maxStatements}") private int MAX_STATEMENTS;
    @Value("${c3p0.testConnection}") private String TEST_CONNECTIONS;
    @Value("${c3p0.maxIdleTime}") private int MAX_IDLE_TIME;
    @Value("${c3p0.acquireIncrement}") private int ACQUIRE_INCREMENT;
    @Value("${c3p0.idleTestPeriod}") private int IDLETESTPERIOD;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setMaxPoolSize(MAX_POOL_SIZE);
        dataSource.setMinPoolSize(MIN_POOL_SIZE);
        dataSource.setAcquireIncrement(ACQUIRE_INCREMENT);
        dataSource.setIdleConnectionTestPeriod(IDLETESTPERIOD);
        dataSource.setMaxStatements(MAX_STATEMENTS);
        dataSource.setMaxIdleTime(MAX_IDLE_TIME);
        dataSource.setJdbcUrl(URL);
        dataSource.setPassword(PASSWORD);
        dataSource.setUser(USERNAME);
        dataSource.setDriverClass(DRIVER);
        return dataSource;
    }
}
