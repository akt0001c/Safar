package com.ms.myapp.models;

public class CardRequest<T> {

    private T request;

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
