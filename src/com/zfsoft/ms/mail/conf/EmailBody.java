package com.zfsoft.ms.mail.conf;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EmailBody implements Serializable {
    /**
     * �ʼ����ݱ���
     */
    protected String encoding = "UTF-8";
    /**
     * �ʼ����ȼ�(1:���� 3:��ͨ 5:��)
     */
    protected String priority;
    /**
     * �ʼ�����
     */
    protected String subject;
    /**
     * �ʼ�����,��ͨ�ı�����html
     */
    protected String content;
    /**
     * �ʼ�������,֧���ⲿ�ʼ�����
     */
    protected InputStream eml;
    /**
     * ���������ƺ�����
     */
    protected EmailFrom from;
    /**
     * �ռ������ƺ�����
     */
    protected Map<String, String> mailto;
    /**
     * ���������ƺ�����
     */
    protected Map<String, String> mailcc;
    /**
     * ���������ƺ�����
     */
    protected Map<String, String> mailBcc;
    /**
     * Ƕ��ͼƬ
     */
    protected Map<String, File> inlineMap;
    /**
     * ����
     */
    protected Map<String, File> attached;
    /**
     * �ʼ������ɹ�����Ӧ��ΨһID
     */
    protected String messageID;

    /**
     *
     */
    public EmailBody() {
        this.mailto = new HashMap<String, String>();
        this.mailcc = new HashMap<String, String>();
        this.mailBcc = new HashMap<String, String>();
    }

    /**
     * @param priority	: �ʼ����ȼ�(1:���� 3:��ͨ 5:��)
     * @param subject 	: �ʼ�����
     * @param content	: �ʼ�����,��ͨ�ı�����html
     * @param from		: ���������ƺ�����
     * @param mailto	: �ռ������ƺ�����
     */
    public EmailBody(String priority, String subject, String content, EmailFrom from, Map<String, String> mailto) {
        this.priority 	= priority;
        this.subject 	= subject;
        this.content 	= content;
        this.from 		= from;
        this.mailto 	= mailto;
    }

    /**
     * @param priority	: �ʼ����ȼ�(1:���� 3:��ͨ 5:��)
     * @param subject 	: �ʼ�����
     * @param content	: �ʼ�����,��ͨ�ı�����html
     * @param from		: ���������ƺ�����
     * @param mailto	: �ռ������ƺ�����
     * @param mailcc	: ���������ƺ�����
     */
    public EmailBody(String priority, String subject, String content,
                     EmailFrom from, Map<String, String> mailto, Map<String, String> mailcc) {
        this.priority = priority;
        this.subject = subject;
        this.content = content;
        this.from = from;
        this.mailto = mailto;
        this.mailcc = mailcc;
    }

    /**
     * @param priority	: �ʼ����ȼ�(1:���� 3:��ͨ 5:��)
     * @param from		: ���������ƺ�����
     * @param mailto	: �ռ������ƺ�����
     * @param mailcc	: ���������ƺ�����
     * @param mailBcc	: ���������ƺ�����
     * @param subject 	: �ʼ�����
     * @param contentBody: �ʼ�����,��ͨ�ı�����html
     */
    public EmailBody(String priority, String subject, String content,
                     EmailFrom from, Map<String, String> mailto, Map<String, String> mailcc,Map<String, String> mailBcc) {
        this.priority = priority;
        this.subject = subject;
        this.content = content;
        this.from = from;
        this.mailto = mailto;
        this.mailcc = mailcc;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmailFrom getFrom() {
        return from;
    }

    public void setFrom(EmailFrom from) {
        this.from = from;
    }

    public void setFrom(String name, String email, boolean notification) {
        this.from = new EmailFrom(name, email, notification);
    }

    public Map<String, String> getMailto() {
        return mailto;
    }

    public void setMailto(Map<String, String> mailto) {
        this.mailto = mailto;
    }

    public void setMailto(String name, String email) {
        this.mailto.put(name, email);
    }

    public Map<String, String> getMailcc() {
        return mailcc;
    }

    public void setMailcc(Map<String, String> mailcc) {
        this.mailcc = mailcc;
    }

    public void setMailcc(String name, String email) {
        this.mailcc.put(name, email);
    }

    public Map<String, String> getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(Map<String, String> mailBcc) {
        this.mailBcc = mailBcc;
    }

    public void setMailBcc(String name, String email) {
        this.mailBcc.put(name, email);
    }

    public Map<String, File> getInlineMap() {
        return inlineMap;
    }

    public void setInlineMap(Map<String, File> inlineMap) {
        this.inlineMap = inlineMap;
    }

    public Map<String, File> getAttached() {
        return attached;
    }

    public void setAttached(Map<String, File> attached) {
        this.attached = attached;
    }

    public InputStream getEml() {
        return eml;
    }

    public void setEml(InputStream eml) {
        this.eml = eml;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
