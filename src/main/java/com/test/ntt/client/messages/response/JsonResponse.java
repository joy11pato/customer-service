package com.test.ntt.client.messages.response;

public class JsonResponse <T>{
    private boolean success;
    private String message;
    private T data;

    public JsonResponse() {}

    public JsonResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonResponse<T> success(String message, T data) {
        return new JsonResponse<>(true, message, data);
    }

    public static <T> JsonResponse<T> error(String message) {
        return new JsonResponse<>(false, message, null);
    }

    // Getters y Setters
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
