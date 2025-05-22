package com.mamadou.bah.si.pmu.domain.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3140599636342313761L;
    private final String code;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
