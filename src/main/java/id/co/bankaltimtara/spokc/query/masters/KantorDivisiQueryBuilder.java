package id.co.bankaltimtara.spokc.query.masters;


import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.QKantorDivisi;

public class KantorDivisiQueryBuilder {

    private BooleanExpression result = null;

    public BooleanExpression getResult() {
        return result;
    }

    public void cari(String cari) {
        if (result == null) {
            result = QKantorDivisi.kantorDivisi.nama.containsIgnoreCase(cari)
                    .or(QKantorDivisi.kantorDivisi.kode.containsIgnoreCase(cari))
                    .or(QKantorDivisi.kantorDivisi.wilayah.nama.containsIgnoreCase(cari));
        } else {
            result = result.or(QKantorDivisi.kantorDivisi.nama.containsIgnoreCase(cari)
                    .or(QKantorDivisi.kantorDivisi.kode.containsIgnoreCase(cari))
                    .or(QKantorDivisi.kantorDivisi.wilayah.nama.containsIgnoreCase(cari)));
        }
    }
}
