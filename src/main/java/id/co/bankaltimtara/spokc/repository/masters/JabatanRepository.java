package id.co.bankaltimtara.spokc.repository.masters;

import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface JabatanRepository extends JpaRepository<Jabatan, Long>, QueryDslPredicateExecutor<Jabatan> {

    Jabatan findByNama(String nama);

    Integer countById(Long id);
}
