package esipe.dataaccess.user.services;

import gokan.ekinci.API;
import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Override
    public void updateBalance(String accountid, Operation o) throws IOException {
       API.get().getRetrofitService().updateBalance(accountid,o).execute();

    }

    @Override
    public List<AccountDto> getAllAccount(String userId) {
        try {
            return API.get().getRetrofitService().accounts(userId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccountDto getAccount(String accountId) throws IOException {
        return API.get().getRetrofitService().getAccount(accountId).execute().body();
    }
}
