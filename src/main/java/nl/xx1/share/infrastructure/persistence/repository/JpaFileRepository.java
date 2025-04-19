package nl.xx1.share.infrastructure.persistence.repository;

import java.util.Optional;
import nl.xx1.share.infrastructure.persistence.entity.JpaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFileRepository extends JpaRepository<JpaFile, Long> {
    Optional<JpaFile> findJpaFileByName(String name);
}
