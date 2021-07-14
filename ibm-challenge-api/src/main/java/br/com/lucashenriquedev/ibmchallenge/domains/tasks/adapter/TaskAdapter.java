package br.com.lucashenriquedev.ibmchallenge.domains.tasks.adapter;

import br.com.lucashenriquedev.ibmchallenge.domains.tasks.controller.request.TaskRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.controller.response.TaskResponse;
import br.com.lucashenriquedev.ibmchallenge.domains.tasks.model.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskAdapter {

    public static Task from(TaskRequest request) {
        return Task.builder()
                .description(request.getDescription())
                .build();
    }

    public static List<TaskResponse> toListResponse(List<Task> list) {
        return list.stream()
                .map(TaskAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .description(task.getDescription())
                .build();
    }

}
