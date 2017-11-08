package esipe.business.user.services;



import esipe.models.AccountDto;
import esipe.models.HistoryDto;
import esipe.models.Operation;

import java.io.IOException;
import java.util.List;

public interface IUserService {

    void updateBalance(String accountid, Operation o) throws IOException;

    List<AccountDto> getAllAccount(String userId);

    AccountDto getAccount(String accountId) throws IOException;

    List<HistoryDto> getHistoryByAccountId(String id) throws IOException;

    List<HistoryDto> getWeeklyHistory(String id) throws IOException;

}
