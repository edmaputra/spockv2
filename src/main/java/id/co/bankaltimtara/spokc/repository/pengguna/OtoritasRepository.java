package id.co.bankaltimtara.spokc.repository.pengguna;

import id.co.bankaltimtara.spokc.model.masters.KantorDivisi;
import id.co.bankaltimtara.spokc.model.users.Otoritas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OtoritasRepository extends JpaRepository<Otoritas, Long>, QueryDslPredicateExecutor<Otoritas> {

    Integer countById(Long id);

}
