package esipe.business.financialAdvisor.services;

import esipe.API;
import esipe.models.AccountDto;
import esipe.models.UserDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

//Gokan TODO: Tu as oublié de placer l'annotation @Service, ton projet ne démarrera pas sans.
@Service
public class FinancialAdvisorService implements IFinancialAdvisorService {
    @Override
    public void createNewCustomer(UserDto userDto) {

        API.get().getRetrofitService().createUser(userDto);
    }

    @Override
    public void updateCustomer(String id,UserDto userDto) {

        API.get().getRetrofitService().updateUser(id,userDto);
    }

    @Override
    public UserDto getCustomerById(String id) throws IOException {

        try {
            return API.get().getRetrofitService().getUserById(id).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UserDto getCustomerByLastName(String lastName) throws IOException {
        return API.get().getRetrofitService().getUserByLastName(lastName).execute().body();
    }

    @Override
    public void createAccount(String id, AccountDto accountDto) throws IOException {

        API.get().getRetrofitService().createAccount(id,accountDto);
    }
}
