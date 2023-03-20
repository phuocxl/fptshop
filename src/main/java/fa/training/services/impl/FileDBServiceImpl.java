package fa.training.services.impl;

import fa.training.entites.FileDB;
import fa.training.repositories.FileDBRepository;
import fa.training.services.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileDBServiceImpl implements FileDBService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Override
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(fileDB);
    }

    @Override
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFile() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public boolean deleteFile(String id) {
        if(id != null) {
            FileDB fileDB = fileDBRepository.findById(id).orElseThrow(RuntimeException::new
            );
            if(fileDB !=null) {
                fileDBRepository.delete(fileDB);
                return true;
            }
        }
    return false;
    }
}
