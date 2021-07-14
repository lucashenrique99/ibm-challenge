package br.com.lucashenriquedev.ibmchallenge.domains.tasks.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponse {

    private Long id;
    private String description;

}
