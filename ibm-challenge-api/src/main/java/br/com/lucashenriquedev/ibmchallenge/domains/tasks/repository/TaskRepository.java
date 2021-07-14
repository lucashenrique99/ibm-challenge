package br.com.lucashenriquedev.ibmchallenge.domains.tasks.repository;

import br.com.lucashenriquedev.ibmchallenge.domains.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
