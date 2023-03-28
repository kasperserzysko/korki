package com.korki.backend.utills;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    private final static String FOLDER_PATH = "D:/kasperserzysko_repository/korki/teacher_photos/";


    public static void saveImage(MultipartFile file, Long teacherId) throws IOException {
        if (file != null) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FOLDER_PATH + teacherId + ".jpg");
            Files.write(path, bytes);
        }
    }
}
