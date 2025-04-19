package nl.xx1.share.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "file")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JpaFile {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigInteger size;

    private String uploadedName;

    private boolean isDownloaded;

    private LocalDateTime createdAt;
}
