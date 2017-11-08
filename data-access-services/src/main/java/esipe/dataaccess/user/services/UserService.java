package esipe.dataaccess.user.services;

import esipe.dataaccess.account.entities.AccountEntity;
import esipe.dataaccess.user.entities.UserEntity;
import esipe.dataaccess.account.repositories.AccountRepository;
import esipe.dataaccess.user.repositories.UserRepository;
import esipe.dataaccess.utils.GenericException;
import esipe.dataaccess.utils.Mapper;

import esipe.models.AccountDto;
import esipe.models.AccountType;
import esipe.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;


	@Autowired
	private Mapper mapper;

	@Autowired
	public UserService(UserRepository userRepository,AccountRepository accountRepository) {
		this.userRepository = userRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public List<UserDto> getAll() {

		return userRepository.findAll()
				.stream()
				.map(
						u -> mapper.userEntityToDto(u)
				)
				.collect(Collectors.toList());

	}

	@Override
	public Optional<UserDto> getUserById(String id) {
		UserEntity userEntity = userRepository.findOne(Long.parseLong(id));

		return (userEntity != null) ?
				Optional.of(mapper.userEntityToDto(userEntity)
				)
				: Optional.empty();


	}

	@Override
	public Optional<UserDto> getUserByLastName(String lastName) {
		UserEntity userEntity = userRepository.getUserByLastName(lastName);

		return (userEntity != null) ?
				Optional.of(mapper.userEntityToDto(userEntity)
				)
				: Optional.empty();
	}

	@Override
	public UserDto create(UserDto userDto) {
		UserEntity userEntity = mapper.userDtoToEntity(userDto);
		userEntity.setFirstName(userDto.getFirstName());



		UserEntity userEntity1 = userRepository.save(userEntity);
		return mapper.userEntityToDto(userEntity1);

	}

	@Override
	public void delete(String id) {
		userRepository.delete(Long.parseLong(id));

	}

	@Override
	public void update(String id, UserDto userDto) {
	UserEntity userEntity = mapper.userDtoToEntity(userDto);
	userEntity.setId(Long.parseLong(id));


	userRepository.save(userEntity);
	}

	@Override
	public AccountDto createAccount(String userId,AccountDto accountDto) {


		getUserById(userId).get().getAccountDtoList().forEach(accountDto1 -> {
			if(accountDto1.getType() == accountDto.getType())
				throw new GenericException("Ce client possède déja ce type de compte");
		});

		if(accountDto.getType().equals(AccountType.valueOf("LivretJeune")) && getUserById(userId).get().getAge() > 18)
			throw new GenericException("Ce client est trop agé pour avoir un livret jeune");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setType(accountDto.getType().toString());
		accountEntity.setUser(mapper.userDtoToEntity(getUserById(userId).get()));


		AccountEntity accountEntity1 = accountRepository.save(accountEntity);

		return mapper.AccountEntityToDto(accountEntity1);

	}

	@Override
	public List<AccountDto> getAllAccount(String userId) {
		List<AccountDto> accountDtoList = getUserById(userId).get().getAccountDtoList();
		System.out.println(accountDtoList.get(0).getAccountNumber());
		return accountDtoList ;
	}
}
