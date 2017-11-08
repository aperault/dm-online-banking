package esipe.business.financialAdvisor.services;

import esipe.API;
import esipe.business.GenericException;
import esipe.models.AccountDto;
import esipe.models.AccountType;
import esipe.models.UserDto;


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
    public void createAccount(String id, AccountDto accountDto) throws IOException {

        getCustomerById(id).getAccountDtoList().forEach(accountDto1 -> {
            if(accountDto1.getType() == accountDto.getType())
                throw new GenericException("Ce client possède déja ce type de compte");
        });

if(accountDto.getType().equals(AccountType.valueOf("LivretJeune")) && getCustomerById(id).getAge() > 18)
    throw new GenericException("Ce client est trop agé pour avoir un livret jeune");

        API.get().getRetrofitService().createAccount(id,accountDto);
    }
}
