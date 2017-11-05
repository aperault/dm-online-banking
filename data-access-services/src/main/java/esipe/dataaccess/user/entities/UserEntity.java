package esipe.dataaccess.user.entities;

import esipe.dataaccess.account.entities.AccountEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;

	@Column (name = "age")
	private Integer age;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(mappedBy = "user")
	private List<AccountEntity> accountList;
}