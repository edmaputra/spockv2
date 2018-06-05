package id.co.bankaltimtara.spokc.query.masters;


import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.QPegawai;
import id.co.bankaltimtara.spokc.model.masters.QUsername;

public class UsernameQueryBuilder {

    private BooleanExpression result = null;

    public BooleanExpression getResult() {
        return result;
    }

    public void cari(String cari) {
        if (result == null) {
            result = QUsername.username.nama.containsIgnoreCase(cari)
                    .or(QUsername.username.tipe.containsIgnoreCase(cari));
        } else {
            result = result.or(QUsername.username.nama.containsIgnoreCase(cari)
                    .or(QUsername.username.tipe.containsIgnoreCase(cari)));
        }
    }
}
