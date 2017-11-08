package esipe.dataaccess.account.services;

import esipe.dataaccess.account.entities.AccountEntity;
import esipe.dataaccess.account.repositories.AccountRepository;
import esipe.dataaccess.history.entities.HistoryEntity;
import esipe.dataaccess.history.repositories.HistoryRepository;
import esipe.dataaccess.utils.GenericException;
import esipe.dataaccess.utils.Mapper;

import esipe.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

        List<HistoryDto> weeklyHistory =getWeeklyOperationsHistory(accountid);
        int sum =0;
        for(HistoryDto h : weeklyHistory){
            if(h.getOperation().getAmount() < 0)
                sum += h.getOperation().getAmount();
        }

        if(sum > Math.abs(800) || o.getAmount() < -800)
            throw new GenericException("Plafond de 800€ dépassé");
        else {


            AccountEntity accountEntity = accountRepository.findOne(Long.parseLong(accountid));
            accountEntity.setId(Long.parseLong(accountid));
            accountEntity.setBalance(o.getAmount() + accountEntity.getBalance());

            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setAmount(o.getAmount());
            historyEntity.setAccount(accountEntity);


            accountRepository.save(accountEntity);
            historyRepository.save(historyEntity);

        }

    }

    @Override
    public List<HistoryDto> getAllOperationsHistory(String id){
        List<HistoryDto> historyDtoList = mapper.historyEntitiesToHistoryDtoList(historyRepository.getAllByAccount(accountRepository.findOne(Long.parseLong(id))));

        return historyDtoList ;

    }

    @Override
    public List<HistoryDto> getWeeklyOperationsHistory(String id) {

        List<HistoryDto> historyDtoList = getAllOperationsHistory(id);
        List<HistoryDto> weeklyHistory = new ArrayList<HistoryDto>();
        historyDtoList.forEach(historyDto -> {
                    if (historyDto.getDate().isBefore( LocalDate.now()) && historyDto.getDate().isAfter(LocalDate.now().minusWeeks(1))){
                        weeklyHistory.add(historyDto);
                    }
                }

        );
        return (weeklyHistory);

    }


    }



