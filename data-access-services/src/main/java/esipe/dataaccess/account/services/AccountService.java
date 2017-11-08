package esipe.dataaccess.account.services;

import esipe.dataaccess.account.entities.AccountEntity;
import esipe.dataaccess.account.repositories.AccountRepository;
import esipe.dataaccess.history.entities.HistoryEntity;
import esipe.dataaccess.history.repositories.HistoryRepository;
import esipe.dataaccess.utils.Mapper;

import esipe.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;



    @Autowired
    private Mapper mapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,HistoryRepository historyRepository) {


        this.accountRepository = accountRepository;
        this.historyRepository = historyRepository;
    }


    @Override
    public AccountDto getAccountById(String id) {
        return mapper.AccountEntityToDto(accountRepository.findOne(Long.parseLong(id)));
    }

    @Override
    public void updateBalance(String accountid, Operation o) {

        AccountEntity accountEntity = accountRepository.findOne(Long.parseLong(accountid));
        accountEntity.setId(Long.parseLong(accountid));
        accountEntity.setBalance(o.getAmount()+accountEntity.getBalance());

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setAmount(o.getAmount());
        historyEntity.setAccount(accountEntity);



        accountRepository.save(accountEntity);
        historyRepository.save(historyEntity);



    }

    @Override
    public List<HistoryDto> getAllOperationsHistory(String id){
        List<HistoryDto> historyDtoList = mapper.historyEntitiesToHistoryDtoList(historyRepository.getAllByAccount(accountRepository.findOne(Long.parseLong(id))));
        System.out.println(historyDtoList.get(0).getId());
        return historyDtoList ;

    }


}
