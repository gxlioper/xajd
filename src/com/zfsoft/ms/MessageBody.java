package com.zfsoft.ms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MessageBody implements Serializable {
    protected MessageType type;
    protected String uuid;
    protected Map<String, String> header = new HashMap();
    protected Map<String, String> body = new HashMap();

    public MessageBody(MessageType type) {
        this.type = type;
    }

    public MessageBody(MessageType type, String uuid) {
        this.type = type;
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, String> getBody() {
        return this.body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }
}
