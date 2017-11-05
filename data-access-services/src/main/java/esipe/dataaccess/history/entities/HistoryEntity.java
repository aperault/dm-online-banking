package esipe.dataaccess.history.entities;


import esipe.dataaccess.account.entities.AccountEntity;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name = "history")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date")
    private Timestamp date = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    private AccountEntity account;



}
