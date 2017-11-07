package esipe.dataaccess.account.services;

import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.HistoryDto;
import gokan.ekinci.models.Operation;

import java.util.List;

public interface IAccountService {

	AccountDto getAccountById(String id);

	void updateBalance(String accountid, Operation o);

	List<HistoryDto> getAllOperationsHistory(String id);

	List<HistoryDto> getWeeklyOperationsHistory(String id);

}