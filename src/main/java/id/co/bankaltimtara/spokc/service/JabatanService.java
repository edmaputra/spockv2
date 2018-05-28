package id.co.bankaltimtara.spokc.service;

import id.co.bankaltimtara.spokc.model.masters.Jabatan;

import java.util.List;

public interface JabatanService extends Services<Jabatan, Long> {

    Jabatan findByNama(String nama);

    List<Jabatan> dapatkan(String q);

}
