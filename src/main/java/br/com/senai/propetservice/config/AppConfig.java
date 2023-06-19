package br.com.senai.propetservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.senai.propetservice.repository.search.SearchRepositoryImpl;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = SearchRepositoryImpl.class, basePackages = "br.com.senai.propetservice.repository")
public class AppConfig {
}
