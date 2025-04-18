package nl.xx1.share.domain.exception;

public class FileNotFound extends Exception {
    public FileNotFound(String fileName) {
        super("File with name '%s' not found.".formatted(fileName));
    }
}
