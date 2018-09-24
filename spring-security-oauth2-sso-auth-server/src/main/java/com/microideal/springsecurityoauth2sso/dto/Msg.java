package com.microideal.springsecurityoauth2sso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Msg<T> {


    private String status;

    @JsonInclude(NON_NULL)
    private String error;

    @JsonInclude(NON_NULL)
    private T data;

}
