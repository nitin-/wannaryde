package com.example.service.impl;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    private static Logger logger = LoggerFactory.getLogger(EmailService.class);
    private static final String VELOCITY_TEMPLATE_FILE_ENCODING = "UTF-8";
    private static String FROM = "support@wannaryde.com";
    private static String REPLY_TO = "support@wannaryde.com" ;
    
    @Autowired
    private MailSender mailSender;

    /*@Autowired
    private VelocityEngine velocityEngine;
*/

    /*private void sendMail(final MimeMessage message) {
        new Thread() {
            public void run() {
                if(mailSender instanceof JavaMailSenderImpl) {
                    ((JavaMailSenderImpl) mailSender).send(message);
                } else {
                    logger.warn("JavaMailSenderImpl is not used");
                }
            }
        }.start();
    }*/

	private void sendMail(MimeMessage message) {
        /*JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)mailSender;
        javaMailSender.send(message);*/
        try {
            Thread mailThread = new Thread(new EmailUtil(message));
            mailThread.start();
        } catch (MailException ex) {
            logger.error("Error in sending email: " + ex, ex);
        }
    }
    /**
     *
     * @param to
     * @param from
     * @param bcc
     * @param cc
     * @param subject
     * @param body
     * @param attachmentName
     * @param resourceStream
     * @param isHtml
     * @throws Exception
     */
    public void sendMail(String[] to, String from, String[] bcc, String[] cc, String subject, String body, String attachmentName, InputStream resourceStream, boolean isHtml) throws Exception {
        JavaMailSenderImpl javaMailSender = ((JavaMailSenderImpl) mailSender);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);

            if (bcc != null) {
                mimeMessageHelper.setBcc(bcc);
            }

            if (cc != null) {
                mimeMessageHelper.setCc(cc);
            }

            mimeMessageHelper.setReplyTo(REPLY_TO);
            setMimeMessage(mimeMessageHelper, from, subject, body, isHtml);

           /* if (resourceStream != null) {
                mimeMessageHelper.addAttachment(attachmentName, new ByteArrayResource(IOUtils.toByteArray(resourceStream)));
            }*/

            sendMail(mimeMessage);
        } catch (MessagingException e) {
            throw new Exception(e);
        }
    }


    public void sendMail(String to, String from, String[] bcc, String[] cc, String subject, String body, boolean isHtml) throws Exception {
        JavaMailSenderImpl javaMailSender = ((JavaMailSenderImpl) mailSender);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);

            if (bcc != null) {
                mimeMessageHelper.setBcc(bcc);
            }

            if (cc != null) {
                mimeMessageHelper.setCc(cc);
            }
            mimeMessageHelper.setReplyTo(REPLY_TO);
            setMimeMessage(mimeMessageHelper, from, subject, body, isHtml);

            sendMail(mimeMessage);
        } catch (MessagingException e) {
            throw new Exception(e);
        }
    }

    /**
     * Sets the common part of the mimemessage from, subject and body
     *
     * @param mimeMessageHelper
     * @param from
     * @param subject
     * @param body
     * @throws javax.mail.MessagingException
     */
    private static void setMimeMessage(MimeMessageHelper mimeMessageHelper, String from, String subject, String body, boolean isHtml) throws MessagingException {
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setReplyTo(REPLY_TO);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body, isHtml);
    }

  
    /**
     * this constructs a body from a jsp template
     *
     * @param request
     * @param response
     * @param servletContext
     * @param templateName
     * @param attributes
     * @return
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public static String constructEmailBodyFromJspTemplate(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, String templateName, Map<String, String> attributes) throws ServletException, IOException {
        if (attributes != null) {
            Set<String> keys = attributes.keySet();
            for (String key : keys) {
                String attr = attributes.get(key);
                request.setAttribute(key, attr);
            }
        }
        RequestDispatcher rd = servletContext.getRequestDispatcher(templateName);
        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
        rd.include(request, wrapper);
        return new String(wrapper.toString());
    }

    /*
     * Wrapper for servlet response to trap the output
     */
    private static class CharResponseWrapper extends HttpServletResponseWrapper {
        private CharArrayWriter output;

        public String toString() {
            return output.toString();
        }

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
            output = new CharArrayWriter();
        }

        public PrintWriter getWriter() {
            return new PrintWriter(output);
        }
    }

    class EmailUtil implements Runnable {

        private MimeMessage mimeMessage;

        EmailUtil(MimeMessage mimeMessage) {
            this.mimeMessage = mimeMessage;
        }

        public void run() {
            JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)mailSender;
            logger.debug("Sending email through : " + javaMailSender.getHost());
            // try email sending for maximum allowable number of trials
            int maxemailtrials = 5;
            for (int i = 1; i <= maxemailtrials; i++) {
                try {
                    javaMailSender.send(this.mimeMessage);
                    logger.info("email sent successfully");
                    break;
                } catch (MailException ex) {
                    if (i == maxemailtrials) {
                        logger.error("Error in sending email: " + ex, ex);
                    }
                }
            }
        }
    }
}
