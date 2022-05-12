package uz.pdp.task_2_6_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false,unique = true)
    private Integer maxsusRaqam;

    private long summa;

    @ManyToOne
    private Bank bank;

    @Size(min = 3,max = 3)
    @Column(nullable = false)
    private short cvv;

    @Column(nullable = false)
    private String fullName;

    private Date amalQilishMuddati;

    @NotNull
    @Column(nullable = false)
    private String password;

    private String kartaTuri;

    private boolean active=false;

}
