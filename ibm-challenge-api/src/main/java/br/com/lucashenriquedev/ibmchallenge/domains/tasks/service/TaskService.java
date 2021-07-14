package br.com.lucashenriquedev.ibmchallenge.domains.tasks.service;

import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.ResourceNotFound;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.messages.TaskMessages;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.model.Task;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task insert(Task task) {
        return repository.save(task);
    }

    public Task update(Long id, Task task) {
        var saved = repository.findById(id).orElseThrow(() -> new ResourceNotFound(TaskMessages.TASK_NOT_FOUND));
        saved.setDescription(task.getDescription());

        return repository.save(saved);
    }

    public Task findByIdOrThrows(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(TaskMessages.TASK_NOT_FOUND));
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFound(TaskMessages.TASK_NOT_FOUND);
        }

        repository.deleteById(id);
    }
}
