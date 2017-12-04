package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.cancel.CanceledUser;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ss on 2017/11/29.
 */
public class EmailUtils {

    private static final String senderName = "syt123450@gmail.com";
    private static final String senderPassword = "syt19930224";

    private static final String SUCCESS_SUBJECT = "Successfully Booking";
    private static final String SUCCESS_MESSAGE = "Hello, you successfully book the ticket right now.";
    private static final String CANCEL_SUBJECT = "Cancel Notification";
    private static final String CANCEL_MESSAGE = "You successfully canceled your ticket right now.";
    private static final String REBOOK_SUBJECT = "Rebook Notification";
    private static final String REBOOK_MESSAGE = "Sorry to rebook your ticket as train canceled.";
    private static final String FAILED_REBOOK_SUBJECT = "Train Cancel Notification";
    private static final String FAILED_REBOOK_MESSAGE = "The train you want to take has been canceled, and tickets has been sold out, sorry about that.";

    private static Session session;
    private static Logger logger = Logger.getLogger(EmailUtils.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    static {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderName, senderPassword);
                    }
                });
    }

    public static void sendSuccessEmail(String emailAddress) {

        send(emailAddress, SUCCESS_SUBJECT, SUCCESS_MESSAGE);
    }

    public static void sendCancelEmail(String emailAddress) {

        send(emailAddress, CANCEL_SUBJECT, CANCEL_MESSAGE);
    }

    public static void sendRebookEmail(List<CanceledUser> rebookUsers) {

        for (CanceledUser rebookUser : rebookUsers) {
            send(rebookUser.getEmail(), REBOOK_SUBJECT, REBOOK_MESSAGE);
        }
    }

    public static void sendFailedRebookEmail(List<CanceledUser> canceledUsers) {

        for (CanceledUser canceledUser : canceledUsers) {
            send(canceledUser.getEmail(), FAILED_REBOOK_SUBJECT, FAILED_REBOOK_MESSAGE);
        }
    }

    private static void send(String address, String subject, String text) {
        executorService.execute(
                new SendTask(address, subject, text)
        );
    }

    private static class SendTask implements Runnable {

        private String address;
        private String subject;
        private String text;

        public SendTask(String address, String subject, String text) {

            this.address = address;
            this.subject = subject;
            this.text = text;
        }

        @Override
        public void run() {
            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderName));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(address));
                message.setSubject(subject);
                message.setText(text);

                Transport.send(message);

                logger.info("Send message to " + address);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}