package esipe.business.financialAdvisor.services;



import esipe.models.AccountDto;
import esipe.models.UserDto;

import java.io.IOException;


public interface IFinancialAdvisorService {

    void createNewCustomer(UserDto userDto);

    void updateCustomer(String id,UserDto userDto);

    UserDto getCustomerById(String id) throws IOException;

    UserDto getCustomerByLastName(String lastName) throws IOException;

    void createAccount(String id, AccountDto accountDto) throws IOException;

}
