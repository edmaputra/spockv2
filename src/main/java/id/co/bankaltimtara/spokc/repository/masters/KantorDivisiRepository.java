package id.co.bankaltimtara.spokc.repository.masters;

import id.co.bankaltimtara.spokc.model.masters.KantorDivisi;
import id.co.bankaltimtara.spokc.model.masters.Wilayah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KantorDivisiRepository extends JpaRepository<KantorDivisi, Long> {

    List<KantorDivisi> findByWilayah(Wilayah wilayah);

}
