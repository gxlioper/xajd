package com.zfsoft.ms.mail;

import com.zfsoft.ms.MessageBody;
import com.zfsoft.ms.MessageOutputProvider;
import com.zfsoft.ms.mail.conf.EmailBody;
import com.zfsoft.ms.mail.impl.JavaMailClientImpl;
import xgxt.action.Base;

import java.util.Properties;

public class EmailMessageOutputProvider implements MessageOutputProvider{

        /**
         *
         * @param data 信息
         * @return
         * @throws Exception
         */
        @Override
        public boolean output(MessageBody data) throws Exception {
                Properties props = System.getProperties();
                if (Base.getInitProperties().get("mailUserName") == null) {
                        throw new IllegalArgumentException("邮件客户端参数: mailUserName not found. ");
                } else if (Base.getInitProperties().get("mailUser") == null) {
                        throw new IllegalArgumentException("邮件客户端参数: mailUser not found. ");
                } else if (data.getBody().get("mailto") == null) {
                        throw new IllegalArgumentException("邮件发送参数: mail.mailto not found. ");
                } else if (data.getBody().get("subject") == null) {
                        throw new IllegalArgumentException("邮件发送参数: mail.subject not found. ");
                } else if (data.getBody().get("content") == null) {
                        throw new IllegalArgumentException("邮件发送参数: mail.content not found. ");
                } else {

                        // 发送服务器需要身份验证
                        props.setProperty(JavaMailKey.MAIL_SMTP_AUTH, "true");
                        // 发送服务器端口，可以不设置，默认是25
                        props.setProperty(JavaMailKey.MAIL_SMTP_PORT, Base.getInitProperties().get("mailPort"));
                        // 发送邮件协议名称
                        props.setProperty(JavaMailKey.MAIL_TRANSPORT_PROTOCOL, Base.getInitProperties().get("mailTransportProtocol"));
                        // 设置邮件服务器主机名
                        props.setProperty(JavaMailKey.MAIL_HOST, Base.getInitProperties().get("mailHost"));
                        //指定使用发送邮件的账户
                        props.setProperty(JavaMailKey.MAIL_USER, Base.getInitProperties().get("mailUser"));
                        props.setProperty(JavaMailKey.MAIL_PASSWORD, Base.getInitProperties().get("mailPasword"));

                        JavaMailClientImpl mailClient = new JavaMailClientImpl();
                        mailClient.setProps(props);

                        EmailBody email = new EmailBody();
                        email.setEncoding("UTF-8");
                        email.setFrom(Base.getInitProperties().get("mailUserName"), Base.getInitProperties().get("mailUser"), false);
                        String sendTo = (String)data.getBody().get("mailto");
                        email.setMailto(sendTo,sendTo);
                        email.setSubject((String)data.getBody().get("subject"));
                        email.setContent((String)data.getBody().get("content"));

                        return mailClient.sendText(email);
                }

        }
}
