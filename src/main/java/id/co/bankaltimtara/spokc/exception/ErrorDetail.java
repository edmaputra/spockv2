package id.co.bankaltimtara.spokc.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;
    private List<String> errors;

    public ErrorDetail(String title, int status, String detail) {
        this.timeStamp = new Date().getTime();
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public ErrorDetail(String title, int status, String detail, List<String> errors) {
        this.timeStamp = new Date().getTime();
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.errors = errors;
    }

    public ErrorDetail(){}
}
