package uz.pdp.task_2_6_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bankomat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private long pulMiqdori;

    private long onlineMoney;

    private String kartaTuri;

    @ManyToOne
    private Bank bank;

    @ManyToMany
    private List<Kupyura> kupyuraList;

    private String address;
}