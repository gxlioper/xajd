package com.zfsoft.ms.mail.conf;

public class EmailFrom {
    /**
     * ����
     */
    protected String name;
    /**
     * ����
     */
    protected String email;
    /**
     * �Ƿ�Ҫ���Ķ���ִ(�ռ����Ķ��ʼ�ʱ����ʾ�ظ�������,�����ʼ����յ�,�����Ķ�)
     */
    protected boolean notification = false;

    public EmailFrom(){}

    public EmailFrom(String name, String email, boolean notification) {
        this.name = name;
        this.email = email;
        this.notification = notification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }
}
