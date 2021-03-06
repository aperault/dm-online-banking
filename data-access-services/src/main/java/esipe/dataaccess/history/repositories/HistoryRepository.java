package esipe.dataaccess.history.repositories;

import esipe.dataaccess.account.entities.AccountEntity;
import esipe.dataaccess.history.entities.HistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends CrudRepository<HistoryEntity,Long> {

    List<HistoryEntity> getAllByAccount_Id(String accountId);

    @Query("SELECT h FROM history h WHERE h.account = :account ")
    List<HistoryEntity> getAllByAccount(@Param("account") AccountEntity account );



}
