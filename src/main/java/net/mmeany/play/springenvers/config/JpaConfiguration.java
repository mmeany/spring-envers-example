package net.mmeany.play.springenvers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableJpaRepositories(
        basePackages = "net.mmeany.play.springenvers.repository",
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                return Optional.of(auth.getName());
            }
            return Optional.of("SYSTEM (0)");
        };
    }
}
