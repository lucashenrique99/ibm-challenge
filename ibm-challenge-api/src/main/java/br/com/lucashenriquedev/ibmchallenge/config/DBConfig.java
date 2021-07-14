package br.com.lucashenriquedev.ibmchallenge.config;

import br.com.lucashenriquedev.ibmchallenge.domains.books.repository.BookRepository;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.repository.TaskRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        BookRepository.class,
        TaskRepository.class
})
public class DBConfig {
}
