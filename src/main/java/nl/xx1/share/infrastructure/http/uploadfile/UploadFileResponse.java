package nl.xx1.share.infrastructure.http.uploadfile;

import nl.xx1.share.domain.entity.File;

import java.math.BigInteger;

public record UploadFileResponse(String name, BigInteger size) {
    public static UploadFileResponse of(File file) {
        return new UploadFileResponse(file.getName(), file.getSize());
    }

    @Override
    public String toString() {
        return "-".repeat(10) + "\n\n" +
                "Uploaded file, %d bytes.".formatted(size) +
                "\n\n" +
                "wget --content-disposition http://127.0.0.1:8080/%s".formatted(name) +
                "\n\n" +
                "-".repeat(10) +
                "\n";
    }
}
