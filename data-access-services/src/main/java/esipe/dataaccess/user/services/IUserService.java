package esipe.dataaccess.user.services;

import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	List<UserDto> getAll();

	Optional<UserDto> getUserById(String id);

	Optional<UserDto> getUserByLastName(String lastName);

	UserDto create(UserDto userDto);

	void delete(String id);

	void update(String id, UserDto userDto);

	AccountDto createAccount(String userId,AccountDto accountDto);

	List<AccountDto> getAllAccount(String userId);

}
