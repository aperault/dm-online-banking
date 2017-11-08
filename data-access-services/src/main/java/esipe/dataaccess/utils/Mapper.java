package esipe.dataaccess.utils;

import esipe.dataaccess.account.entities.AccountEntity;
import esipe.dataaccess.history.entities.HistoryEntity;
import esipe.dataaccess.user.entities.UserEntity;


import esipe.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();


    public UserDto userEntityToDto(UserEntity userEntity) {

        return UserDto.builder()
                .id(String.valueOf(userEntity.getId()))
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .address(userEntity.getAddress())
                .phoneNumber(userEntity.getPhoneNumber())
                .accountDtoList(accountEntitiesToAccountDtoList(userEntity.getAccountList()))
                .build();

    }

    public List<AccountDto> accountEntitiesToAccountDtoList(List<AccountEntity> accountEntities){
        return accountEntities
                .stream()
                .map(
                        a -> AccountDto.builder()
                                .accountNumber(String.valueOf(a.getId()))
                                .type(Enum.valueOf(AccountType.class,a.getType()))
                                .balance(a.getBalance())
                                .historyList(historyEntitiesToHistoryDtoList(a.getHistoryList()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<HistoryDto> historyEntitiesToHistoryDtoList(List<HistoryEntity> historyEntities){
        return historyEntities
                .stream()
                .map(
                        h -> HistoryDto.builder()
                        .id(String.valueOf(h.getId()))
                        .date(h.getDate().toLocalDateTime().toLocalDate())
                                .operation(Operation.builder().amount(h.getAmount()).build())
                        .build()

                )
                .collect(Collectors.toList());

    }

    public UserEntity userDtoToEntity(UserDto userDto){

        UserEntity userEntity = new UserEntity();

        if(userDto.getId()  != null)
        userEntity.setId(Long.parseLong(userDto.getId()));

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setAge(userDto.getAge());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());


        return userEntity;

    }

    public AccountDto AccountEntityToDto(AccountEntity accountEntity){
        return AccountDto.builder()
                .accountNumber(String.valueOf(accountEntity.getId()))
                .type(Enum.valueOf(AccountType.class,accountEntity.getType()))
                .balance(accountEntity.getBalance())
                .historyList(historyEntitiesToHistoryDtoList(accountEntity.getHistoryList()))
                .build();

    }

    public AccountEntity AccountDtoToEntity(AccountDto accountDto){

        AccountEntity accountEntity = new AccountEntity();

        if(accountDto.getAccountNumber() != null)
        accountEntity.setId(Long.parseLong(accountDto.getAccountNumber()));

        accountEntity.setBalance(accountDto.getBalance());

        return accountEntity;
    }

    public HistoryDto HistoryEntityToDto(HistoryEntity historyEntity){
        return HistoryDto.builder()
                .id(String.valueOf(historyEntity.getId()))
                .date(historyEntity.getDate().toLocalDateTime().toLocalDate())
                .operation(Operation.builder().amount(historyEntity.getAmount()).build())
                .build();
    }

}
