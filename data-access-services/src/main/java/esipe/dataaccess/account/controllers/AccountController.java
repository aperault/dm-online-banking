package esipe.dataaccess.account.controllers;


import esipe.dataaccess.account.services.AccountService;
import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.HistoryDto;
import gokan.ekinci.models.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Operation o) {
        accountService.updateBalance(id,o);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(path = "/{id}/history", method = RequestMethod.GET)
    public ResponseEntity<?> getAllOperationsHistory(@PathVariable String id) {
        List<HistoryDto> historyDtoList = accountService.getAllOperationsHistory(id);
        System.out.println(historyDtoList.get(0).getId());
        return (!historyDtoList.isEmpty()) ?
                new ResponseEntity<>(historyDtoList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/{id}/weeklyhistory", method = RequestMethod.GET)
    public ResponseEntity<?> getWeeklyOperationsHistory(@PathVariable String id) {
        List<HistoryDto> historyDtoList = accountService.getAllOperationsHistory(id);
        List<HistoryDto> weeklyHistory = new ArrayList<HistoryDto>();
        historyDtoList.forEach(historyDto -> {
            if (historyDto.getDate().isBefore( LocalDate.now()) && historyDto.getDate().isAfter(LocalDate.now().minusWeeks(1))){
                weeklyHistory.add(historyDto);
            }
                }

        );
        System.out.println(historyDtoList.get(0).getId());
        return (!weeklyHistory.isEmpty()) ?
                new ResponseEntity<>(weeklyHistory, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountById(@PathVariable String id) {
    return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
    }

}
