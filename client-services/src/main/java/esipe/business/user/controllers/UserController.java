package esipe.business.user.controllers;


import esipe.business.user.services.UserService;

import esipe.models.AccountDto;
import esipe.models.HistoryDto;
import esipe.models.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/account/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Operation o) throws IOException {

            userService.updateBalance(id, o);
            return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(path = "/users/{id}/account", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccount(@PathVariable String id) {

        List<AccountDto> result = userService.getAllAccount(id);

        return (result != null) ?
                new ResponseEntity<>(result,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/account/{id}/history", method = RequestMethod.GET)
    public ResponseEntity<?> getHistoryForThisAccount(@PathVariable String id) throws IOException {

        List<HistoryDto> result = userService.getHistoryByAccountId(id);

        return (result != null) ?
                new ResponseEntity<>(result,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
