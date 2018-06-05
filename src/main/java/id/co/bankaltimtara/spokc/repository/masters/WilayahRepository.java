package id.co.bankaltimtara.spokc.repository.masters;

import id.co.bankaltimtara.spokc.model.masters.Wilayah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface WilayahRepository extends JpaRepository<Wilayah, Long>, QueryDslPredicateExecutor<Wilayah> {

    Integer countById(Long id);

}
