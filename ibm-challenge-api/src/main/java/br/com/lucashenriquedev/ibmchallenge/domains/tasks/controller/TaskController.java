package br.com.lucashenriquedev.ibmchallenge.domains.tasks.controller;

import br.com.lucashenriquedev.ibmchallenge.domains.tasks.adapter.TaskAdapter;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.controller.request.TaskRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.controller.response.TaskResponse;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse insert(@RequestBody @Valid TaskRequest request) {
        return TaskAdapter.toResponse(
                service.insert(TaskAdapter.from(request)));
    }

    @PatchMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody @Valid TaskRequest request) {
        return TaskAdapter.toResponse(
                service.update(id, TaskAdapter.from(request)));
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return TaskAdapter.toResponse(service.findByIdOrThrows(id));
    }

    @GetMapping
    public List<TaskResponse> findAll() {
        return TaskAdapter.toListResponse(service.findAll());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
