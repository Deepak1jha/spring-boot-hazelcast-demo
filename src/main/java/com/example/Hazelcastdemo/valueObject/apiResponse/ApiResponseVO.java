package com.example.Hazelcastdemo.valueObject.apiResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponseVO {

    private String responseType;
    private String message;
    private Object data;

    public ApiResponseVO(String responseType, String message, Object data) {
        this.responseType = responseType;
        this.message = message;
        this.data = data;
    }

}
