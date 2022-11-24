package com.kurtcan.javacore.utilities.result.concretes;

import com.kurtcan.javacore.utilities.result.abstracts.IDataResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DataResult<T> extends Result implements IDataResult<T> {
    private T data = null;

    public DataResult(boolean status, String message, T data) {
        super(status, message);
        this.data = data;
    }

    public DataResult(boolean status, String message) {
        super(status, message);
    }

    public DataResult(boolean status) {
        super(status);
    }

    public Result toResult() {
        return new Result(this.getStatus(), this.getMessage());
    }

}
