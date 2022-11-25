package com.kurtcan.javacore.controller.abstracts;

import com.kurtcan.javacore.utilities.result.abstracts.IResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseApiController implements IBaseController {

    public ResponseEntity<?> toHttpResponse(IResult result) {
        if (result.getStatus()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
