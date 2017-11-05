package esipe.dataaccess.user.services;

import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.Operation;

import java.io.IOException;
import java.util.List;

public interface IUserService {

    void updateBalance(String accountid, Operation o) throws IOException;

    List<AccountDto> getAllAccount(String userId);

    AccountDto getAccount(String accountId) throws IOException;

}
