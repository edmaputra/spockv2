package id.co.bankaltimtara.spokc.repository.pengguna;


import id.co.bankaltimtara.spokc.model.masters.KantorDivisi;
import id.co.bankaltimtara.spokc.model.users.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Pengguna, Long>, QueryDslPredicateExecutor<Pengguna> {

    @Query("SELECT DISTINCT user FROM Pengguna user " +
            "INNER JOIN FETCH user.authorities AS authorities " +
            "WHERE user.username = :username")
    Pengguna findByUsername(@Param("username") String username);

    Integer countById(Long id);

}
