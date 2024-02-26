package com.robertZhou.chat.commons.utils;

import com.robertZhou.chat.commons.exception.MailException;
import com.robertZhou.chat.commons.returnresult.RespCode;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class MailUtils {

    public static Map<String,Integer> codesMap=new ConcurrentHashMap<>();
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    private ThreadPoolExecutor executor;

    public void sendMail(String email,String clientName,Integer code){
        //线程包裹防止等待
        executor.submit(()->{
            // 创建一个邮件消息
            MimeMessage message = javaMailSender.createMimeMessage();

            // 创建 MimeMessageHelper
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, false);
                // 发件人邮箱和名称
                helper.setFrom(username, "MyChat官方");
                // 收件人邮箱
                helper.setTo(email);
                // 邮件标题
                helper.setSubject("【MyChat】您的找回密码");
                // 邮件正文，第二个参数表示是否是HTML正文
                helper.setText("你好"+clientName+"您的验证码是<strong>"+code+"</strong>", true);

                // 发送
                javaMailSender.send(message);
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new MailException(RespCode.MAIL_ERROR);
            }
            codesMap.put(clientName,code);
        });
    }
}
