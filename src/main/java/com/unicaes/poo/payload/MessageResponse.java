package com.unicaes.poo.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Data
@ToString
public class MessageResponse implements Serializable {
    private String message;
    private Object data;
}
