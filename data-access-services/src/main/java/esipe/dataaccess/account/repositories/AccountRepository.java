package esipe.dataaccess.account.repositories;

import esipe.dataaccess.account.entities.AccountEntity;
import esipe.dataaccess.history.entities.HistoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE account a SET a.balance = a.balance + :amount where a.id = :id ")
    void updateBalance(@Param("id") Long id, @Param("amount") Integer amount);

}
