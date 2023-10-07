package com.lcwd.electronic.store.dtos;

import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NamedEntityGraph
@AllArgsConstructor
@Builder
public class ApiResponseMessage {

    private String message;
    private boolean success;

    private HttpStatus status;
}
