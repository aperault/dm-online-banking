package esipe.business.user.services;

import esipe.API;
import esipe.business.GenericException;
import esipe.models.AccountDto;
import esipe.models.HistoryDto;
import esipe.models.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Override
    public void updateBalance(String accountid, Operation o) throws IOException {


        List<HistoryDto> weeklyHistory = getWeeklyHistory(accountid);
        int sum =0;
        for(HistoryDto h : weeklyHistory){
            if(h.getOperation().getAmount() < 0)
            sum += h.getOperation().getAmount();
        }

        if(sum > Math.abs(800) || o.getAmount() < -800)
          throw new GenericException("Plafond de 800€ dépassé");
        else {

            API.get().getRetrofitService().updateBalance(accountid,o).execute();
        }






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

    @Override
    public List<HistoryDto> getHistoryByAccountId(String id)  {
        try {
            return API.get().getRetrofitService().getHistoryByAccountId(id).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HistoryDto> getWeeklyHistory(String id) throws IOException {
        List<HistoryDto> weeklyHistory = API.get().getRetrofitService().getWeeklyHistory(id).execute().body();
        if(weeklyHistory != null)
            return weeklyHistory;
        else return Collections.emptyList();
    }
}
