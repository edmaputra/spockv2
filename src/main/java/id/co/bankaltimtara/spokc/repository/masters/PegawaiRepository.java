package id.co.bankaltimtara.spokc.repository.masters;


import id.co.bankaltimtara.spokc.model.masters.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PegawaiRepository extends JpaRepository<Pegawai, Long>, QueryDslPredicateExecutor<Pegawai> {

    Integer countById(Long id);

}
