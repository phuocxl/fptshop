package fa.training.services;

import fa.training.entites.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileDBService {
    FileDB store(MultipartFile file) throws IOException;
    FileDB getFile(String id);
    Stream<FileDB> getAllFile();
    boolean deleteFile(String id);
}
