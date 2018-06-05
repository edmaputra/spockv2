package id.co.bankaltimtara.spokc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceTidakDitemukanException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Long resourceId;

    public ResourceTidakDitemukanException() {}

    public ResourceTidakDitemukanException(String message) {
        super(message);
    }

    public ResourceTidakDitemukanException(Long id, String message) {
        super(message);
        this.resourceId = id;
    }
    public ResourceTidakDitemukanException(String message, Throwable cause) {
        super(message, cause);
    }


}
