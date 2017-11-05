package esipe.dataaccess.history.repositories;

import esipe.dataaccess.history.entities.HistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<HistoryEntity,Long> {

    List<HistoryEntity> getAllByAccount_Id(String accountId);

}
