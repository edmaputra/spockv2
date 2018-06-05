package id.co.bankaltimtara.spokc.query.masters;


import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.QKantorDivisi;
import id.co.bankaltimtara.spokc.model.masters.QWilayah;

public class WilayahQueryBuilder {

    private BooleanExpression result = null;

    public BooleanExpression getResult() {
        return result;
    }

    public void cari(String cari) {
        if (result == null) {
            result = QWilayah.wilayah.nama.containsIgnoreCase(cari)
                    .or(QWilayah.wilayah.kode.containsIgnoreCase(cari));
        } else {
            result = result.or(QWilayah.wilayah.nama.containsIgnoreCase(cari)
                    .or(QWilayah.wilayah.kode.containsIgnoreCase(cari)));
        }
    }
}
