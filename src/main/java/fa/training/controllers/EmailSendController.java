package fa.training.controllers;

import fa.training.entites.EmailDetails;
import fa.training.services.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-mail")
public class EmailSendController {
    @Autowired
    private EmailSendService emailSendService;

    @PostMapping("/")
    public String sendMail(@RequestBody EmailDetails emailDetails){
        String status = emailSendService.sendSimpleMail(emailDetails);
        return status;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details){
        String status = emailSendService.sendMailWithAttachment(details);
        return status;
    }
}
