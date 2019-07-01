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

        LOG.debug("׼���ʼ�����...");

        // ����Sessionʵ������
        Session session = JavaMailUtils.getSession(props);

        // ����MimeMessageʵ������
        MimeMessage mimeMessage = new SMTPMessage(session);

        LOG.debug("����ƽ̨��" + props.getProperty(JavaMailKey.MAIL_HOST_DESC, "unknown"));

        // �����ʼ�����
        mimeMessage.setSubject(email.getSubject());
        // ���÷���ʱ��
        mimeMessage.setSentDate(new Date());
        // ���÷������ʼ�
        setAntispam(mimeMessage, email);

        LOG.debug("�����ˣ�" + email.getFrom().getName() + " <" + email.getFrom().getEmail() + ">");

        // ���÷����ˣ������ý���˴�����������������
        InternetAddress from = new InternetAddress("\"" + MimeUtility.encodeText(email.getFrom().getName()) + "\" <" + email.getFrom().getEmail() + ">");
        mimeMessage.setFrom(from);
        // ���ûظ���(�ռ��˻ظ����ʼ�ʱ,Ĭ���ռ���)
        mimeMessage.setReplyTo(new InternetAddress[] {from});
        // Ҫ���Ķ���ִ(�ռ����Ķ��ʼ�ʱ����ʾ�ظ�������,�����ʼ����յ�,�����Ķ�)
        if(email.getFrom().isNotification()){
            mimeMessage.setHeader(HEADER_DISPOSITION_NOTIFICATION_TO, email.getFrom().getEmail());
        }

        // �����ռ��˵���Ƭ�͵�ַ
        if(!BlankUtils.isBlank(email.getMailto())){
            List<String> mailtoList = new ArrayList<String>();
            for (String mailto : email.getMailto().keySet()) {
                // ���һ���ռ���
                mailtoList.add("\"" + MimeUtility.encodeText(mailto) + "\" <" + email.getMailto().get(mailto) + ">");
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(StringUtils.join(mailtoList, ",")));
            LOG.debug("�ռ��ˣ�" + StringUtils.join(mailtoList, ","));
        }

        // ���ó����˵���Ƭ�͵�ַ
        if(!BlankUtils.isBlank(email.getMailcc())){
            List<String> mailccList = new ArrayList<String>();
            for (String mailcc : email.getMailcc().keySet()) {
                // ���һ������
                mailccList.add("\"" + MimeUtility.encodeText(mailcc) + "\" <" + email.getMailcc().get(mailcc) + ">");
            }
            mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(StringUtils.join(mailccList, ",")));
            LOG.debug("�����ˣ�" + StringUtils.join(mailccList, ","));
        }

        // ���������˵���Ƭ�͵�ַ
        if(!BlankUtils.isBlank(email.getMailBcc())){
            List<String> mailBccList = new ArrayList<String>();
            for (String mailcc : email.getMailBcc().keySet()) {
                // ���һ������
                mailBccList.add("\"" + MimeUtility.encodeText(mailcc) + "\" <" + email.getMailBcc().get(mailcc) + ">");
            }
            mimeMessage.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(StringUtils.join(mailBccList, ",")));
            LOG.debug("�����ˣ�" + StringUtils.join(mailBccList, ","));
        }

        if (isHtml) {

            // ���� ��������ı���ͼƬ��"related"�͵�MimeMultipart����
            Multipart mainPart = new MimeMultipart(MULTIPART_SUBTYPE_RELATED);
            // ����һ������HTML���ݵ�MimeBodyPart
            BodyPart mimePart = new MimeBodyPart();
            // ����HTML���ݺ͸�ʽ/���뷽ʽ
            mimePart.setContent(email.getContent(), CONTENT_TYPE_HTML + CONTENT_TYPE_CHARSET_SUFFIX + email.getEncoding() );
            //��BodyPart���뵽MimeMultipart������(���Լ�����BodyPart)
            mainPart.addBodyPart(mimePart);


            // ��Ƕ��ͼƬ
            if (email.getInlineMap() != null && !email.getInlineMap().isEmpty()) {
                for (String inlineFilename : email.getInlineMap().keySet()) {
                    // �����ͼ
                    mainPart.addBodyPart(createImageMimeBodyPart(inlineFilename, email.getInlineMap().get(inlineFilename)));
                }
            }
            // �и���
            if (email.getAttached() != null && !email.getAttached().isEmpty()) {
                for (String attachmentFilename : email.getAttached().keySet()) {
                    // ���븽��
                    mainPart.addBodyPart(createAttachmentBodyPart(attachmentFilename, email.getInlineMap().get(attachmentFilename)));
                }
            }

            // ��MiniMultipart��������Ϊ�ʼ�����
            mimeMessage.setContent(mainPart);
        }
        else {
            // ���ô��ı�����Ϊ�ʼ�����
            mimeMessage.setText(email.getContent());
        }
        // �����ʼ�
        if (Boolean.parseBoolean(props.getProperty(JavaMailKey.MAIL_SMTP_AUTH, "false"))) {
            String user = props.getProperty(JavaMailKey.MAIL_SMTP_USER, props.getProperty(JavaMailKey.MAIL_USER));
            String password = props.getProperty(JavaMailKey.MAIL_SMTP_PASSWORD, props.getProperty(JavaMailKey.MAIL_PASSWORD));
            Transport.send(mimeMessage, user, password);
        } else{
            Transport.send(mimeMessage);
        }
        email.setMessageID(mimeMessage.getMessageID());

        LOG.debug("�ʼ����ͳɹ���");
    }

    // �����ǶͼƬ
    protected MimeBodyPart createImageMimeBodyPart(String imageName, File path)
            throws MessagingException, UnsupportedEncodingException {
        DataSource fds = new FileDataSource(path);
        MimeBodyPart mbp = new MimeBodyPart();
        DataHandler dh = new DataHandler(fds);
        mbp.setDataHandler(dh);
        // ���ö�Ӧ����Դ�ļ���Ψһ��ʶ������ MIME Э������ʼ��Ľṹ��֯��ʽ�е� Content-ID ͷ�ֶΣ�
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
        // ����ķ������úͲ���ͼƬ�ǲ�ͬ�ģ�ʹ��MimeUtility.encodeWord()������������Ƶ���������
        attch.setFileName(MimeUtility.encodeWord(fileName));
        return attch;
    }

    /**
     * ���÷������ʼ�
     */
    public void setAntispam(Message message,EmailBody email) throws MessagingException {

        //=============�������ʼ�����====================
        // �������ȼ�(1:���� 3:��ͨ 5:��)
        message.setHeader(HEADER_PRIORITY, email != null ? StringUtils.getSafeStr(email.getPriority(), "3") : "3");
        message.setHeader(HEADER_MSMAIL_PRIORITY, "Normal");
        // �����ʼ���ַ��ͷ��Ϣ����outlook�����;
        message.setHeader(HEADER_MAILER, "Microsoft Outlook Express 6.00.2900.2869");
        message.setHeader(HEADER_MIMEOLE, "Produced By Microsoft MimeOLE V6.00.2900.2869");
        message.setHeader(HEADER_DATE, format.format(new Date()));

    }

    public void setProps(Properties props){
        this.props = props;
    }
}
