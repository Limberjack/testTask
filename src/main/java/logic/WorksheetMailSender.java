package logic;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Component
public class WorksheetMailSender{

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;


    public void sendMessageWithAttachment(User user) throws MessagingException, IOException, DocumentException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(user.getEmail());
        helper.setSubject("worksheet receiver");
        helper.setText("Here is your worksheet");
        FileSystemResource file
                = new FileSystemResource(PDFMaker.generatePdfFromHtml(user));
        helper.addAttachment("worksheet.pdf", file);
        emailSender.send(message);
    }
}
