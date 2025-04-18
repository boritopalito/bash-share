package nl.xx1.share.infrastructure.persistence.mapper;

import nl.xx1.share.domain.entity.File;
import nl.xx1.share.infrastructure.persistence.entity.JpaFile;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public File of(JpaFile jpaFile) {
        return File.builder()
                .id(jpaFile.getId())
                .name(jpaFile.getName())
                .uploadedName(jpaFile.getUploadedName())
                .size(jpaFile.getSize())
                .isDownloaded(jpaFile.isDownloaded())
                .createdAt(jpaFile.getCreatedAt())
                .build();
    }

    public JpaFile of(File file) {
        return JpaFile.builder()
                .id(file.getId())
                .name(file.getName())
                .uploadedName(file.getUploadedName())
                .size(file.getSize())
                .isDownloaded(file.isDownloaded())
                .createdAt(file.getCreatedAt())
                .build();
    }
}
