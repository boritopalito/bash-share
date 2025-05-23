package nl.xx1.share.infrastructure.http.uploadfile;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import nl.xx1.share.application.port.in.uploadfile.UploadFileParameters;
import nl.xx1.share.application.port.in.uploadfile.UploadFileResult;
import nl.xx1.share.application.port.in.uploadfile.UploadFileUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UploadFileAction {
    private final UploadFileUseCase uploadFileUseCase;

    public UploadFileAction(UploadFileUseCase uploadFileUseCase) {
        this.uploadFileUseCase = uploadFileUseCase;
    }

    @PutMapping("{filename}")
    public ResponseEntity<String> uploadFile(HttpServletRequest request, @PathVariable String filename)
            throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        UploadFileParameters uploadFileParameters = new UploadFileParameters(filename, inputStream);
        UploadFileResult result = uploadFileUseCase.execute(uploadFileParameters);
        UploadFileResponse response = UploadFileResponse.of(result.file());
        return ResponseEntity.ok(response.toString());
    }
}
