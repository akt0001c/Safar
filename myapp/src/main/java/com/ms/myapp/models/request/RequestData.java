package com.ms.myapp.models.request;



public class RequestData {
    private String requestType;

    private Payload<Object> payload;

    public Payload<Object> getPayload() {
        return payload;
    }

    public void setPayload(Payload<Object> payload) {
        this.payload = payload;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

}
