package nl.xx1.share.application.service;

import nl.xx1.share.application.port.in.downloadfile.DownloadFileParameters;
import nl.xx1.share.application.port.in.downloadfile.DownloadFileResult;
import nl.xx1.share.application.port.in.downloadfile.DownloadFileUseCase;
import nl.xx1.share.domain.entity.File;
import nl.xx1.share.domain.exception.FileAlreadyDownloaded;
import nl.xx1.share.domain.exception.FileNotFound;
import nl.xx1.share.domain.repository.FileRepository;
import org.springframework.stereotype.Component;

@Component
public class DownloadFileService implements DownloadFileUseCase {
    private final FileRepository fileRepository;

    public DownloadFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public DownloadFileResult execute(DownloadFileParameters parameters) throws FileNotFound, FileAlreadyDownloaded {
        String fileName = parameters.name();

        File file = fileRepository.getByFileName(fileName);

        if (file.isDownloaded()) {
            throw new FileAlreadyDownloaded("File with id '%d' is already downloaded.".formatted(file.getId()));
        }

        file.markDownloaded();

        fileRepository.save(file);

        return new DownloadFileResult(file);
    }
}
