package com.github.fabriciolfj.exceptions.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MessageErrorDetails {

    private String field;
    private String message;
}
