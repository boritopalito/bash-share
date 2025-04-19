package nl.xx1.share.infrastructure.http.uploadfile;

import java.math.BigInteger;
import nl.xx1.share.domain.entity.File;

public record UploadFileResponse(String name, BigInteger size) {
    public static UploadFileResponse of(File file) {
        return new UploadFileResponse(file.getName(), file.getSize());
    }

    @Override
    public String toString() {
        return "-".repeat(10) + "\n\n" + "Uploaded file, %d bytes.".formatted(size)
                + "\n\n"
                + "wget --content-disposition http://127.0.0.1:8080/%s".formatted(name)
                + "\n\n"
                + "-".repeat(10)
                + "\n";
    }
}
