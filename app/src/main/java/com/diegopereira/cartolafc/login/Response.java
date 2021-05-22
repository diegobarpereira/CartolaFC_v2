package com.diegopereira.cartolafc.login;

public class Response {
    final String id;
    final String userMessage;
    final String glbId;

    public Response(String id, String userMessage, String glbId) {
        this.id = id;
        this.userMessage = userMessage;
        this.glbId = glbId;
    }
}
