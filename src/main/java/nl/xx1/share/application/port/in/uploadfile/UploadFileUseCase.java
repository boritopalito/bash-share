package nl.xx1.share.application.port.in.uploadfile;

import java.io.IOException;

public interface UploadFileUseCase {
    UploadFileResult execute(UploadFileParameters parameters) throws IOException;
}
