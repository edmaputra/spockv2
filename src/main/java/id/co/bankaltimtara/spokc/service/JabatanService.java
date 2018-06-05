package id.co.bankaltimtara.spokc.service;

import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JabatanService extends Services<Jabatan, Long> {

    Jabatan findByNama(String nama);

}
