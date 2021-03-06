package com.zfsoft.ms.mail.util;

import com.zfsoft.ms.mail.JavaMailKey;
import com.zfsoft.ms.mail.author.PropsAuthenticator;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public abstract class JavaMailUtils {

    /**
     * 获取邮件发送期间上下文环境信息，如服务器的主机名、端口号、协议名称等
     */
    public static Session getSession(Properties props) {
        Session ret = null;
        if (Boolean.parseBoolean(props.getProperty(JavaMailKey.MAIL_SMTP_AUTH, "false"))) {
            ret = Session.getInstance(props, new PropsAuthenticator(props));
        } else {
            ret = Session.getInstance(props);
        }
        return ret;
    }

    public static String getRootURL(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";
    }
}
