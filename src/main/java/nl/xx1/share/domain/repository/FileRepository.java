package nl.xx1.share.domain.repository;

import nl.xx1.share.domain.entity.File;
import nl.xx1.share.domain.exception.FileNotFound;

public interface FileRepository {
    File save(File file);

    File getByFileName(String fileName) throws FileNotFound;
}
