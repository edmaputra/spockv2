package id.co.bankaltimtara.spokc.query.masters;


import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.QKantorDivisi;
import id.co.bankaltimtara.spokc.model.masters.QPegawai;

public class PegawaiQueryBuilder {

    private BooleanExpression result = null;

    public BooleanExpression getResult() {
        return result;
    }

    public void cari(String cari) {
        if (result == null) {
            result = QPegawai.pegawai.nama.containsIgnoreCase(cari)
                    .or(QPegawai.pegawai.nomorRegister.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.jabatan.nama.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.kantorDivisi.nama.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.pengguna.username.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.username.any().nama.containsIgnoreCase(cari));
        } else {
            result = result.or(QPegawai.pegawai.nama.containsIgnoreCase(cari)
                    .or(QPegawai.pegawai.nomorRegister.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.jabatan.nama.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.kantorDivisi.nama.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.pengguna.username.containsIgnoreCase(cari))
                    .or(QPegawai.pegawai.username.any().nama.containsIgnoreCase(cari)));
        }
    }
}
