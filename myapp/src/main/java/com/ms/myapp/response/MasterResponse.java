package com.ms.myapp.response;

import lombok.Data;

@Data
public class MasterResponse<T> {
    private T response;
}
