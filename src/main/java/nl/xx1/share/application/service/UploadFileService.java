package nl.xx1.share.application.service;

import nl.xx1.share.application.port.in.uploadfile.UploadFileParameters;
import nl.xx1.share.application.port.in.uploadfile.UploadFileResult;
import nl.xx1.share.application.port.in.uploadfile.UploadFileUseCase;
import org.springframework.stereotype.Component;

@Component
public class UploadFileService implements UploadFileUseCase {
    @Override
    public UploadFileResult execute(UploadFileParameters parameters) {
        return null;
    }
}
