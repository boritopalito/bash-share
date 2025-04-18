package nl.xx1.share.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Getter
public class File {
    private Long id;

    private String name;

    private BigInteger size;

    private String uploadedName;

    @Builder.Default
    private boolean isDownloaded = false;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public void markDownloaded() {
        this.isDownloaded = true;
    }
}
