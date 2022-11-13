package com.kurtcan.javacore.utilities.result.abstracts;

public interface IDataResult<T> extends IResult {
    T getData();
    Result toResult();
}
