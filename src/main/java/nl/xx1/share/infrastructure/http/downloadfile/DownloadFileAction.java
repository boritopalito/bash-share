package nl.xx1.share.infrastructure.http.downloadfile;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import nl.xx1.share.application.port.in.downloadfile.DownloadFileParameters;
import nl.xx1.share.application.port.in.downloadfile.DownloadFileResult;
import nl.xx1.share.application.port.in.downloadfile.DownloadFileUseCase;
import nl.xx1.share.domain.entity.File;
import nl.xx1.share.domain.exception.FileAlreadyDownloaded;
import nl.xx1.share.domain.exception.FileNotFound;
import nl.xx1.share.infrastructure.browser.RequestTypeDetector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/{filename}")
public class DownloadFileAction {
    private final DownloadFileUseCase downloadFileUseCase;
    private final RequestTypeDetector requestTypeDetector;
    private final String storageLocation;

    public DownloadFileAction(
            DownloadFileUseCase downloadFileUseCase,
            RequestTypeDetector requestTypeDetector,
            @Value("${storage.location}") String storageLocation) {
        this.downloadFileUseCase = downloadFileUseCase;
        this.requestTypeDetector = requestTypeDetector;
        this.storageLocation = storageLocation;
    }

    @GetMapping
    public Object downloadFile(
            @PathVariable String filename,
            @RequestParam(defaultValue = "false") boolean force,
            HttpServletRequest request) {

        boolean isBrowserRequest = requestTypeDetector.isBrowserRequest(request);

        if (isBrowserRequest && !force) {
            return new ModelAndView("download");
        }

        DownloadFileParameters parameters = new DownloadFileParameters(filename);
        try {
            DownloadFileResult result = downloadFileUseCase.execute(parameters);
            File file = result.file();
            Path filePath = Path.of(storageLocation).resolve(file.getName());
            Resource resource = new FileSystemResource(filePath.toFile());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getUploadedName())
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getSize()))
                    .body(resource);
        } catch (FileNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (FileAlreadyDownloaded e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
