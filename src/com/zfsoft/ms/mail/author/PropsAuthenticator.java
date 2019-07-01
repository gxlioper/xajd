package com.zfsoft.ms.mail.author;

import com.zfsoft.ms.mail.JavaMailKey;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class PropsAuthenticator extends Authenticator {

    protected Properties props = new Properties();

    public PropsAuthenticator(Properties props) {
        this.props = props;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        // ”√ªß√˚°¢√‹¬Î
        String userName = props.getProperty(JavaMailKey.MAIL_SMTP_USER, props.getProperty(JavaMailKey.MAIL_USER));
        String password = props.getProperty(JavaMailKey.MAIL_SMTP_PASSWORD, props.getProperty(JavaMailKey.MAIL_PASSWORD));
        return new PasswordAuthentication(userName, password);
    }

}
