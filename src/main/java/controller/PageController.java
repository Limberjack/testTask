package controller;

import com.lowagie.text.DocumentException;
import logic.User;
import logic.WorksheetMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class PageController {
    @Autowired
    WorksheetMailSender worksheetMailSender;

    @GetMapping("/worksheet")
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "worksheet";
    }

    @PostMapping("/worksheet")
    public String getSubmit(@ModelAttribute User user, Model model){

        System.out.println(user.getEmail());
        model.addAttribute("user", new User());
        try {
            worksheetMailSender.sendMessageWithAttachment(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return "worksheet";
    }
}