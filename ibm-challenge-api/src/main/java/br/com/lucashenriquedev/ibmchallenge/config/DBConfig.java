package br.com.lucashenriquedev.ibmchallenge.config;

import br.com.lucashenriquedev.ibmchallenge.domains.books.repository.BookRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        BookRepository.class
})
public class DBConfig {
}
