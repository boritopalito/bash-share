package nl.xx1.share.application.port.in.downloadfile;

import nl.xx1.share.domain.exception.FileAlreadyDownloaded;
import nl.xx1.share.domain.exception.FileNotFound;

public interface DownloadFileUseCase {
    DownloadFileResult execute(DownloadFileParameters parameters) throws FileNotFound, FileAlreadyDownloaded;
}
