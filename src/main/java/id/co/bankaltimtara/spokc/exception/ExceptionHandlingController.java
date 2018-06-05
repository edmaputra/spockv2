package id.co.bankaltimtara.spokc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceTidakDitemukan(ResourceNotFoundException ex){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setDetail(ex.getMessage());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        errorDetail.setErrors(errors);
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> invalidInput(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);

    }
}
