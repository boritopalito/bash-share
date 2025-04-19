package nl.xx1.share.application.port.in.uploadfile;

import java.io.InputStream;

public record UploadFileParameters(String filename, InputStream inputStream) {}
