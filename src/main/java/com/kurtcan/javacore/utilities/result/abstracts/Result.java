package com.kurtcan.javacore.utilities.result.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Result implements IResult {
    private Boolean status;
    private String message = "";

    public Result(boolean status) {
        this.status = status;
    }
}
