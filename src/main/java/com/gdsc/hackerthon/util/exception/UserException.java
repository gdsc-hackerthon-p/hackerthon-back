package com.gdsc.hackerthon.util.exception;

import com.gdsc.hackerthon.util.api.ResponseCode;

public class UserException extends BaseException{
    public UserException(ResponseCode responseCode) {
        super(responseCode);
    }
}
