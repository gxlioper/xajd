package com.zfsoft.ms;

import java.util.Locale;

public enum MessageType {
    EMAIL("email", "�ʼ���Ϣ"),
    SMS("sms", "������Ϣ"),
    SOCKET("socket", "Socket��Ϣ"),
    MQ("mq", "��Ϣ����");

    protected String name;
    protected String desc;

    private MessageType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    static MessageType valueOfIgnoreCase(String parameter, String desc) {
        MessageType parm = valueOf(parameter.toUpperCase(Locale.ENGLISH).trim());
        parm.desc = desc;
        return parm;
    }
}
