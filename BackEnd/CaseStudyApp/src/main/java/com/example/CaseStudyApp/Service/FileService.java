package com.example.CaseStudyApp.Service;

import com.example.CaseStudyApp.Dao.IFileDao;
import com.example.CaseStudyApp.Entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    private IFileDao fileDao;

    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File File = new File(fileName, file.getContentType(), file.getBytes());

        return fileDao.save(File);
    }

    public File getFile(String id) {
        return fileDao.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileDao.findAll().stream();
    }
}

