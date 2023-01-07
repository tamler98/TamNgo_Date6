package repository;

import entity.AccountEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    List<AccountEntity> findAll();
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "Delete from account where balance = 0", nativeQuery = true)
    void deleteAccountNotHasBalance();
    @Modifying
    @Transactional(rollbackFor = Exception.class, noRollbackFor = NullPointerException.class)
    @Query(value = "update account as a set a.looked = false\n" +
            "where a.accept_time = curdate()", nativeQuery = true)
    void updateAccountHasAcceptDateEqualNow();
}
