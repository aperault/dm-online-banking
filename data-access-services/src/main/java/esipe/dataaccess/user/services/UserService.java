package esipe.dataaccess.user.services;

import esipe.dataaccess.account.entities.AccountEntity;
import esipe.dataaccess.user.entities.UserEntity;
import esipe.dataaccess.account.repositories.AccountRepository;
import esipe.dataaccess.user.repositories.UserRepository;
import esipe.dataaccess.utils.Mapper;
import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

		/*return userRepository.findAll()
			.stream()
			.map(
				u -> UserDto.builder()
				.id(String.valueOf(u.getId()))
				.firstName(u.getFirstName())
				.lastName(u.getLastName())
				.build()
			)
			.collect(Collectors.toList());*/
	}

	@Override
	public Optional<UserDto> getUserById(String id) {
		UserEntity userEntity = userRepository.findOne(Long.parseLong(id));

		return (userEntity != null) ?
				Optional.of(mapper.userEntityToDto(userEntity)
				)
				: Optional.empty();

	/*	List<AccountDto> accountDtoList = userEntity.getAccountList()
		.stream()
				.map(
						a -> AccountDto.builder()
						.accountNumber(String.valueOf(a.getId()))
						.type(Enum.valueOf(AccountType.class,a.getType()))
						.balance(a.getBalance())
						.build()
				)
				.collect(Collectors.toList());


		return (userEntity != null) ?
			Optional.of(
				UserDto.builder()
					.id(String.valueOf(userEntity.getId()))
					.firstName(userEntity.getFirstName())
					.lastName(userEntity.getLastName())
						.address(userEntity.getAddress())
						.phoneNumber(userEntity.getPhoneNumber())
						.accountDtoList(accountDtoList)
					.build()
			)
			: Optional.empty();*/
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
		/*UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setAddress(userDto.getAddress());
		userEntity.setPhoneNumber(userDto.getPhoneNumber());*/


		UserEntity userEntity1 = userRepository.save(userEntity);
		return mapper.userEntityToDto(userEntity1);
		/*return UserDto.builder()
			.id(String.valueOf(userEntity1.getId()))
			.firstName(userEntity1.getFirstName())
			.lastName(userEntity1.getLastName())
				.address(userEntity1.getAddress())
				.phoneNumber(userEntity1.getPhoneNumber())
			.build();*/
	}

	@Override
	public void delete(String id) {
		userRepository.delete(Long.parseLong(id));

	}

	@Override
	public void update(String id, UserDto userDto) {
	UserEntity userEntity = mapper.userDtoToEntity(userDto);
	userEntity.setId(Long.parseLong(id));

	/*	UserEntity userEntity = new UserEntity();

		List<AccountEntity> accountEntityList = userDto.getAccountDtoList()
				.stream()
				.map(
						a -> AccountEntity.builder()
						.id(Long.parseLong(a.getAccountNumber()))
						.type(a.getType().toString())
						.balance(a.getBalance())
						.user(mapper.userDtoToEntity(userDto))
						.build()
				).collect(Collectors.toList());
		userEntity.setId(Long.parseLong(userDto.getId()));
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setAddress(userDto.getAddress());
		userEntity.setPhoneNumber(userDto.getPhoneNumber());
		userEntity.setAccountList(accountEntityList);*/
	userRepository.save(userEntity);
	}

	@Override
	public AccountDto createAccount(String userId,AccountDto accountDto) {



		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setType(accountDto.getType().toString());
		accountEntity.setUser(mapper.userDtoToEntity(getUserById(userId).get()));


		AccountEntity accountEntity1 = accountRepository.save(accountEntity) ;

		return mapper.AccountEntityToDto(accountEntity1);

		/*return AccountDto.builder()
				.accountNumber(String.valueOf(accountEntity1.getId()))
				.type(Enum.valueOf(AccountType.class,accountEntity1.getType()))
				.balance(accountEntity1.getBalance())
				.build();*/
	}

	@Override
	public List<AccountDto> getAllAccount(String userId) {
		List<AccountDto> accountDtoList = getUserById(userId).get().getAccountDtoList();
		System.out.println(accountDtoList.get(0).getAccountNumber());
		return accountDtoList ;
	}
}
