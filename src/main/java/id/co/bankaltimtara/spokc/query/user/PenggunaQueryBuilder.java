package id.co.bankaltimtara.spokc.query.user;


import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.QJabatan;
import id.co.bankaltimtara.spokc.model.users.QPengguna;

public class PenggunaQueryBuilder {

    private BooleanExpression result = null;

    public BooleanExpression getResult() {
        return result;
    }

    public void cari(String cari) {
        if (result == null) {
            result = QPengguna.pengguna.username.containsIgnoreCase(cari)
                .or(QPengguna.pengguna.authorities.any().nama.containsIgnoreCase(cari));
        } else {
            System.out.println("cari ada");
            result = result.or(QPengguna.pengguna.username.containsIgnoreCase(cari)
                    .or(QPengguna.pengguna.authorities.any().nama.containsIgnoreCase(cari)));
        }
    }
}
