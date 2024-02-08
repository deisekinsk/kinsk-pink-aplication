package com.kinsk.pink.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

        private Date timestamp = new Date();
        private String status = "error";
        private int statusCode = 400;
        private String error;


}
