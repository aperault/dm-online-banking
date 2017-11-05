package esipe.dataaccess.user.controllers;


import esipe.dataaccess.user.services.UserService;
import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.Operation;
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

        userService.updateBalance(id,o);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/users/{id}/account", method = RequestMethod.GET)
    public ResponseEntity<?> update(@PathVariable String id) {

        List<AccountDto> result = userService.getAllAccount(id);

        return (result != null) ?
                new ResponseEntity<>(result,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
