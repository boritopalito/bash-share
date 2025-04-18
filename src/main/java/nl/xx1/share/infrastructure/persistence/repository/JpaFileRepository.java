package nl.xx1.share.infrastructure.persistence.repository;

import nl.xx1.share.infrastructure.persistence.entity.JpaFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaFileRepository extends JpaRepository<JpaFile, Long> {
    Optional<JpaFile> findJpaFileByName(String name);
}
