package com.kurtcan.javacore.utilities.result.abstracts;

import com.kurtcan.javacore.utilities.result.concretes.Result;

public interface IDataResult<T> extends IResult {
    T getData();
    Result toResult();
}
