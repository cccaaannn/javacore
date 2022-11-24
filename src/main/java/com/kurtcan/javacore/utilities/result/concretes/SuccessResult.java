package com.kurtcan.javacore.utilities.result.concretes;

import com.kurtcan.javacore.utilities.result.abstracts.IResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SuccessResult extends Result implements IResult {
    public SuccessResult(String message) {
        super(true, message);
    }

    public SuccessResult() {
        super(true);
    }
}
