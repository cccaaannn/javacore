package com.kurtcan.javacore.utilities.http.response.concrete;

import com.kurtcan.javacore.utilities.result.abstracts.IResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpApiResponseBuilder {

    public static ResponseEntity<?> toHttpResponse(IResult result) {
        if(result.getStatus()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    
}
