package id.co.bankaltimtara.spokc.predicate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {

    private String key;

    private String operation;

    private Object value;

    public SearchCriteria(){

    }

    public SearchCriteria(String key, String operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

}
