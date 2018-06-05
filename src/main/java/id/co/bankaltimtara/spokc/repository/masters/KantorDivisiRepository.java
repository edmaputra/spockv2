package id.co.bankaltimtara.spokc.repository.masters;

import id.co.bankaltimtara.spokc.model.masters.KantorDivisi;
import id.co.bankaltimtara.spokc.model.masters.Wilayah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface KantorDivisiRepository extends JpaRepository<KantorDivisi, Long>, QueryDslPredicateExecutor<KantorDivisi> {

    List<KantorDivisi> findByWilayah(Wilayah wilayah);

    Integer countById(Long id);

}
