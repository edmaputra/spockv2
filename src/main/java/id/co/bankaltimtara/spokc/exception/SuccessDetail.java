package id.co.bankaltimtara.spokc.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SuccessDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;

    public SuccessDetail(String title, int status, String detail) {
        this.timeStamp = new Date().getTime();
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public SuccessDetail(){}
}
