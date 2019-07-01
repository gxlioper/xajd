package com.zfsoft.ms.mail.impl;

import com.sun.mail.smtp.SMTPMessage;
import com.zfsoft.ms.mail.JavaMailClient;
import com.zfsoft.ms.mail.JavaMailKey;
import com.zfsoft.ms.mail.conf.EmailBody;
import com.zfsoft.ms.mail.util.BlankUtils;
import com.zfsoft.ms.mail.util.JavaMailUtils;
import com.zfsoft.ms.mail.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class JavaMailClientImpl implements JavaMailClient{

    protected SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    protected Properties props;

    protected Logger LOG = LoggerFactory.getLogger(JavaMailClientImpl.class);

    protected static final String MULTIPART_SUBTYPE_MIXED = "mixed";

    protected static final String MULTIPART_SUBTYPE_RELATED = "related";

    protected static final String MULTIPART_SUBTYPE_ALTERNATIVE = "alternative";

    protected static final String CONTENT_TYPE_ALTERNATIVE = "text/alternative";

    protected static final String CONTENT_TYPE_HTML = "text/html";

    protected static final String CONTENT_TYPE_CHARSET_SUFFIX = ";charset=";

    protected static final String HEADER_PRIORITY = "X-Priority";

    protected static final String HEADER_CONTENT_ID = "Content-ID";

    @Override
    public boolean sendText(EmailBody email) throws Exception {
        try {
            sendMail(email, false);
            return true;
        } catch (MessagingException ex) {
            LOG.error(ex.getMessage());
        }
        return false;
    }

    protected void sendMail(EmailBody email,boolean isHtml) throws Exception{

        LOG.debug("准备邮件发送...");

        // 创建Session实例对象
        Session session = JavaMailUtils.getSession(props);

        // 创建MimeMessage实例对象
        MimeMessage mimeMessage = new SMTPMessage(session);

        LOG.debug("发件平台：" + props.getProperty(JavaMailKey.MAIL_HOST_DESC, "unknown"));

        // 设置邮件主题
        mimeMessage.setSubject(email.getSubject());
        // 设置发送时间
        mimeMessage.setSentDate(new Date());
        // 设置反垃圾邮件
        setAntispam(mimeMessage, email);

        LOG.debug("发件人：" + email.getFrom().getName() + " <" + email.getFrom().getEmail() + ">");

        // 设置发件人，此设置解决了大多数邮箱的垃圾拦截
        InternetAddress from = new InternetAddress("\"" + MimeUtility.encodeText(email.getFrom().getName()) + "\" <" + email.getFrom().getEmail() + ">");
        mimeMessage.setFrom(from);
        // 设置回复人(收件人回复此邮件时,默认收件人)
        mimeMessage.setReplyTo(new InternetAddress[] {from});
        // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读)
        if(email.getFrom().isNotification()){
            mimeMessage.setHeader(HEADER_DISPOSITION_NOTIFICATION_TO, email.getFrom().getEmail());
        }

        // 设置收件人的名片和地址
        if(!BlankUtils.isBlank(email.getMailto())){
            List<String> mailtoList = new ArrayList<String>();
            for (String mailto : email.getMailto().keySet()) {
                // 添加一个收件人
                mailtoList.add("\"" + MimeUtility.encodeText(mailto) + "\" <" + email.getMailto().get(mailto) + ">");
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(StringUtils.join(mailtoList, ",")));
            LOG.debug("收件人：" + StringUtils.join(mailtoList, ","));
        }

        // 设置抄送人的名片和地址
        if(!BlankUtils.isBlank(email.getMailcc())){
            List<String> mailccList = new ArrayList<String>();
            for (String mailcc : email.getMailcc().keySet()) {
                // 添加一个抄送
                mailccList.add("\"" + MimeUtility.encodeText(mailcc) + "\" <" + email.getMailcc().get(mailcc) + ">");
            }
            mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(StringUtils.join(mailccList, ",")));
            LOG.debug("抄送人：" + StringUtils.join(mailccList, ","));
        }

        // 设置密送人的名片和地址
        if(!BlankUtils.isBlank(email.getMailBcc())){
            List<String> mailBccList = new ArrayList<String>();
            for (String mailcc : email.getMailBcc().keySet()) {
                // 添加一个密送
                mailBccList.add("\"" + MimeUtility.encodeText(mailcc) + "\" <" + email.getMailBcc().get(mailcc) + ">");
            }
            mimeMessage.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(StringUtils.join(mailBccList, ",")));
            LOG.debug("密送人：" + StringUtils.join(mailBccList, ","));
        }

        if (isHtml) {

            // 创建 用于组合文本和图片，"related"型的MimeMultipart对象
            Multipart mainPart = new MimeMultipart(MULTIPART_SUBTYPE_RELATED);
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart mimePart = new MimeBodyPart();
            // 设置HTML内容和格式/编码方式
            mimePart.setContent(email.getContent(), CONTENT_TYPE_HTML + CONTENT_TYPE_CHARSET_SUFFIX + email.getEncoding() );
            //将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
            mainPart.addBodyPart(mimePart);


            // 有嵌入图片
            if (email.getInlineMap() != null && !email.getInlineMap().isEmpty()) {
                for (String inlineFilename : email.getInlineMap().keySet()) {
                    // 加入插图
                    mainPart.addBodyPart(createImageMimeBodyPart(inlineFilename, email.getInlineMap().get(inlineFilename)));
                }
            }
            // 有附件
            if (email.getAttached() != null && !email.getAttached().isEmpty()) {
                for (String attachmentFilename : email.getAttached().keySet()) {
                    // 加入附件
                    mainPart.addBodyPart(createAttachmentBodyPart(attachmentFilename, email.getInlineMap().get(attachmentFilename)));
                }
            }

            // 将MiniMultipart对象设置为邮件内容
            mimeMessage.setContent(mainPart);
        }
        else {
            // 设置纯文本内容为邮件正文
            mimeMessage.setText(email.getContent());
        }
        // 发送邮件
        if (Boolean.parseBoolean(props.getProperty(JavaMailKey.MAIL_SMTP_AUTH, "false"))) {
            String user = props.getProperty(JavaMailKey.MAIL_SMTP_USER, props.getProperty(JavaMailKey.MAIL_USER));
            String password = props.getProperty(JavaMailKey.MAIL_SMTP_PASSWORD, props.getProperty(JavaMailKey.MAIL_PASSWORD));
            Transport.send(mimeMessage, user, password);
        } else{
            Transport.send(mimeMessage);
        }
        email.setMessageID(mimeMessage.getMessageID());

        LOG.debug("邮件发送成功！");
    }

    // 添加内嵌图片
    protected MimeBodyPart createImageMimeBodyPart(String imageName, File path)
            throws MessagingException, UnsupportedEncodingException {
        DataSource fds = new FileDataSource(path);
        MimeBodyPart mbp = new MimeBodyPart();
        DataHandler dh = new DataHandler(fds);
        mbp.setDataHandler(dh);
        // 设置对应的资源文件的唯一标识符，即 MIME 协议对于邮件的结构组织格式中的 Content-ID 头字段；
        mbp.setHeader("Content-ID", imageName);
        mbp.setFileName(MimeUtility.encodeText(fds.getName()));
        return mbp;
    }

    protected MimeBodyPart createAttachmentBodyPart(String fileName, File path)
            throws MessagingException, UnsupportedEncodingException {
        DataSource fds = new FileDataSource(path);
        DataHandler dh = new DataHandler(fds);
        MimeBodyPart attch = new MimeBodyPart();
        attch.setDataHandler(dh);
        // 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题
        attch.setFileName(MimeUtility.encodeWord(fileName));
        return attch;
    }

    /**
     * 设置反垃圾邮件
     */
    public void setAntispam(Message message,EmailBody email) throws MessagingException {

        //=============反垃圾邮件处理====================
        // 设置优先级(1:紧急 3:普通 5:低)
        message.setHeader(HEADER_PRIORITY, email != null ? StringUtils.getSafeStr(email.getPriority(), "3") : "3");
        message.setHeader(HEADER_MSMAIL_PRIORITY, "Normal");
        // 声明邮件地址和头信息披上outlook的马甲;
        message.setHeader(HEADER_MAILER, "Microsoft Outlook Express 6.00.2900.2869");
        message.setHeader(HEADER_MIMEOLE, "Produced By Microsoft MimeOLE V6.00.2900.2869");
        message.setHeader(HEADER_DATE, format.format(new Date()));

    }

    public void setProps(Properties props){
        this.props = props;
    }
}
