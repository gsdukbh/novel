package top.werls.novel.config.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author leejiawei
 */
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class JpaConfig {

}
