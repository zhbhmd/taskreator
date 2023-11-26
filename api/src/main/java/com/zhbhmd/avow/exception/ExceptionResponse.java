package com.zhbhmd.avow.exception;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    String code;
    String message;
    String description;
}
