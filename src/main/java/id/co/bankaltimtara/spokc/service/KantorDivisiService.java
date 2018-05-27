package id.co.bankaltimtara.spokc.service;

import id.co.bankaltimtara.spokc.model.masters.KantorDivisi;
import id.co.bankaltimtara.spokc.model.masters.Wilayah;

import java.util.List;

public interface KantorDivisiService extends Services<KantorDivisi, Long> {

    List<KantorDivisi> findByWilayah(Wilayah wilayah);

}
