package esipe.dataaccess.user.services;

import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.UserDto;

import java.io.IOException;


public interface IFinancialAdvisorService {

    void createNewCustomer(UserDto userDto);

    void updateCustomer(String id,UserDto userDto);

    UserDto getCustomerById(String id) throws IOException;

    UserDto getCustomerByLastName(String lastName) throws IOException;

    void createAccount(String id, AccountDto accountDto);

}
