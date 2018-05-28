package id.co.bankaltimtara.spokc.repository.masters;

import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JabatanRepository extends JpaRepository<Jabatan, Long>, JpaSpecificationExecutor<Jabatan> {


    Jabatan findByNama(String nama);
}
