package com.unicaes.poo.payload;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MessageResponse<T> implements Serializable {

    private String message;
    private T data;

    public static <T> MessageResponse<T> build(String message, T data) {
        return MessageResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }
}
