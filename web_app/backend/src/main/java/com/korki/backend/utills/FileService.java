package com.korki.backend.utills;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService {

    private final static String FOLDER_PATH = "D:/kasperserzysko_repository/korki/teacher_photos/";

    public static void saveImage(MultipartFile multipartFile, Long teacherId) throws IOException {
            Path uploadPath = Paths.get(FOLDER_PATH);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(FOLDER_PATH + teacherId);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file!");
            }
        }

    public static byte[] getImage(Long teacherId) throws IOException {
        try {
            return Files.readAllBytes(Path.of(FOLDER_PATH + teacherId));
        } catch (IOException e) {
            throw new IOException("Couldn't get an image!");
        }
    }
}
