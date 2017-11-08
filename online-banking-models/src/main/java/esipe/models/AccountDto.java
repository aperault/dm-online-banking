package esipe.models;


import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDto {

    private String accountNumber;
    private AccountType type;
    private int balance;
    private List<HistoryDto> historyList;

}

