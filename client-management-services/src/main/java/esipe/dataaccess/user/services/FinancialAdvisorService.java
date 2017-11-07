package esipe.dataaccess.user.services;

import gokan.ekinci.API;
import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.UserDto;

import java.io.IOException;

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
    public void createAccount(String id, AccountDto accountDto) {
        API.get().getRetrofitService().createAccount(id,accountDto);
    }
}
