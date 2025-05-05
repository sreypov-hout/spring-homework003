package com.example.huot_sreypov_spring_homework003.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String message;
    private T payload;
    private Object status;
    private LocalDateTime timestamp;
}
