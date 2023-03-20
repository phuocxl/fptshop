package fa.training.controllers;

import fa.training.entites.FileDB;
import fa.training.model.DataRespose;
import fa.training.model.ResponseFile;
import fa.training.services.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
@CrossOrigin("http://localhost:8080")
public class FileController {
    @Autowired
    private FileDBService fileDBService;
    @PostMapping("/upload")
    public ResponseEntity<DataRespose> uploadFile(@RequestPart("file") MultipartFile file) {
        String message = "";
        try {
            fileDBService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new DataRespose(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new DataRespose(message));
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileDBService.getAllFile().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileDBService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataRespose> delete(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataRespose(fileDBService.deleteFile(id))
        );
    }
}
