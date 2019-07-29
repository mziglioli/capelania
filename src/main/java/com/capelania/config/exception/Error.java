package com.capelania.config.exception;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error implements Serializable {

    private String key;
    private String value;
}