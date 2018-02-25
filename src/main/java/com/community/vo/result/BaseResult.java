package com.community.vo.result;

import java.util.HashMap;
import java.util.Map;

public class BaseResult {
    private boolean success;
    private String message;
    private Map<String, String> errors = new HashMap<>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
