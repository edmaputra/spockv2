package id.co.bankaltimtara.spokc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ResponseService {

    public ResponseService(){

    }

    public SuccessDetail ok(String title, String detail){
        return new SuccessDetail(
                title,
                HttpStatus.OK.value(),
                detail
        );
    }

    public SuccessDetail created(String title, String detail){
        return new SuccessDetail(
                title,
                HttpStatus.CREATED.value(),
                detail
        );
    }

    public ErrorDetail badGateway(String title, String detail){
        return new ErrorDetail(
                title,
                HttpStatus.BAD_GATEWAY.value(),
                detail
        );
    }

    public ErrorDetail badRequest(String title, String detail){
        return new ErrorDetail(
                title,
                HttpStatus.BAD_REQUEST.value(),
                detail
        );
    }

    public ErrorDetail notFound(String title, String detail){
        List<String> errors = new ArrayList<>(Arrays.asList(detail));
        return new ErrorDetail(
                title,
                HttpStatus.NOT_FOUND.value(),
                detail,
                errors
        );
    }



}
