package esipe.models;


import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    @Pattern(regexp = "[0-9]{1,}")
    private String id;
    private String lastName;
    private String firstName;
    private Integer age;
    private String address;
    private String phoneNumber;
    private List<AccountDto> accountDtoList;
}
