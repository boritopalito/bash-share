package nl.xx1.share.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;
import nl.xx1.share.application.port.in.uploadfile.UploadFileParameters;
import nl.xx1.share.application.port.in.uploadfile.UploadFileResult;
import nl.xx1.share.application.port.in.uploadfile.UploadFileUseCase;
import nl.xx1.share.domain.entity.File;
import nl.xx1.share.domain.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadFileService implements UploadFileUseCase {

    @Value("${storage.location}")
    private String storageLocation;

    private final FileRepository fileRepository;

    public UploadFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public UploadFileResult execute(UploadFileParameters parameters) throws IOException {
        InputStream inputStream = parameters.inputStream();

        String filename = parameters.filename();

        Path tempFilePath = Files.createTempFile(filename, "tmp");
        Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);

        String dstFileName = UUID.randomUUID().toString();

        java.io.File tempFile = tempFilePath.toFile();

        File file = File.builder()
                .uploadedName(filename)
                .name(dstFileName)
                .size(BigInteger.valueOf(tempFile.length()))
                .createdAt(LocalDateTime.now())
                .build();

        Path storageDirectory = Path.of(storageLocation);
        Path dstPath = storageDirectory.resolve(dstFileName);

        Files.move(tempFilePath, dstPath, StandardCopyOption.REPLACE_EXISTING);

        File savedFile = fileRepository.save(file);

        return new UploadFileResult(savedFile);
    }
}
