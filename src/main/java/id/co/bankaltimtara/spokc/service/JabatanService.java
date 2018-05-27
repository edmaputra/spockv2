package id.co.bankaltimtara.spokc.service;

import id.co.bankaltimtara.spokc.model.masters.Jabatan;

public interface JabatanService extends Services<Jabatan, Long> {

    Jabatan findByNama(String nama);

}
