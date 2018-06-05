package id.co.bankaltimtara.spokc.query.masters;


import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.QJabatan;

public class JabatanQueryBuilder {

    private BooleanExpression result = null;

    public BooleanExpression getResult() {
        return result;
    }

    public void cari(String cari) {
        if (result == null) {
            result = QJabatan.jabatan.nama.containsIgnoreCase(cari);
        } else {
            System.out.println("cari ada");
            result = result.or(QJabatan.jabatan.nama.containsIgnoreCase(cari));
        }
    }

    public void level(Integer level) {
        if (result == null) {
            result = QJabatan.jabatan.level.eq(level);
        } else {
            System.out.println("Level ada");
            result = result.or(QJabatan.jabatan.level.eq(level));
        }
    }
}
