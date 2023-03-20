package fa.training.services;

import fa.training.entites.EmailDetails;

public interface EmailSendService {
    String sendSimpleMail(EmailDetails emailDetails);

    String sendMailWithAttachment(EmailDetails emailDetails);

}
