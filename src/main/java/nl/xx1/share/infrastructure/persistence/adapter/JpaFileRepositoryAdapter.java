package nl.xx1.share.infrastructure.persistence.adapter;

import nl.xx1.share.domain.entity.File;
import nl.xx1.share.domain.exception.FileNotFound;
import nl.xx1.share.domain.repository.FileRepository;
import nl.xx1.share.infrastructure.persistence.entity.JpaFile;
import nl.xx1.share.infrastructure.persistence.mapper.FileMapper;
import nl.xx1.share.infrastructure.persistence.repository.JpaFileRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaFileRepositoryAdapter implements FileRepository {
    private final FileMapper fileMapper;
    private final JpaFileRepository jpaFileRepository;

    public JpaFileRepositoryAdapter(FileMapper fileMapper, JpaFileRepository jpaFileRepository) {
        this.fileMapper = fileMapper;
        this.jpaFileRepository = jpaFileRepository;
    }

    @Override
    public File save(File file) {
        JpaFile jpaFile = fileMapper.of(file);
        JpaFile savedJpaFile = jpaFileRepository.save(jpaFile);
        return fileMapper.of(savedJpaFile);
    }

    @Override
    public File getByFileName(String fileName) throws FileNotFound {
        return jpaFileRepository.findJpaFileByName(fileName).map(fileMapper::of).orElseThrow(() -> new FileNotFound(fileName));
    }
}
