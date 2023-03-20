package fa.training.controllers;

import fa.training.factory.doc.MailMergeToPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;

@RestController
@RequestMapping("/mail-merge")
public class MailMergeToPayController {

    @Autowired
    private MailMergeToPay mailMergeToPay;

    @PostMapping("/mail-merge-to-pay")
    public ResponseEntity<Resource> mailMergeToPay() throws Exception {
        String pdfPath = "";
        try {
            pdfPath = mailMergeToPay.MailMergeData();
            File file = new File(pdfPath);
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

            HttpHeaders headers = new HttpHeaders();
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=to-pay.pdf";
            headers.set(headerKey, headerValue);
            return ResponseEntity.ok().headers(headers).contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        } finally {
            File file = new File(pdfPath);
            Files.delete(file.toPath());
        }
    }

}
