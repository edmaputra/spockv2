package id.co.bankaltimtara.spokc.repository.masters;

import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JabatanRepository extends JpaRepository<Jabatan, Long> {


    Jabatan findByNama(String nama);
}
