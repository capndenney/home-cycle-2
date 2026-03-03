package com.home_cycle.data.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {

    private Integer statusCode;
    private String message;
    private Date timestamp;
    private String errorCode;
}
