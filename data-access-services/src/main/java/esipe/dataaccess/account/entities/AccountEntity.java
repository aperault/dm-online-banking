package esipe.dataaccess.account.entities;

import esipe.dataaccess.history.entities.HistoryEntity;
import esipe.dataaccess.user.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;


    @Column(name = "balance")
    private Integer balance = 0;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "account")
    private List<HistoryEntity> historyList;

}
