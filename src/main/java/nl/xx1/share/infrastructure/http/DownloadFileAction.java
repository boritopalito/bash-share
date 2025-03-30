package nl.xx1.share.infrastructure.http;

import jakarta.servlet.http.HttpServletRequest;
import nl.xx1.share.infrastructure.browser.RequestTypeDetector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{filename}")
public class DownloadFileAction {
    private final RequestTypeDetector requestTypeDetector;

    public DownloadFileAction(RequestTypeDetector requestTypeDetector) {
        this.requestTypeDetector = requestTypeDetector;
    }

    @GetMapping
    public ResponseEntity<?> downloadFile(@PathVariable String filename,
                                          HttpServletRequest request) {

        boolean isBrowserRequest = requestTypeDetector.isBrowserRequest(request);

        if (isBrowserRequest) {
            return ResponseEntity.ok("Browser");
        }

        return ResponseEntity.ok("Not browser");
    }
}
