package id.co.bankaltimtara.spokc.repository.masters;

import id.co.bankaltimtara.spokc.model.masters.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface UsernameRepository extends JpaRepository<Username, Long>, QueryDslPredicateExecutor<Username> {
    Integer countById(Long id);
}
