package uz.pdp.task_2_6_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GetMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne
    private Bankomat bankomat;

    @ManyToOne
    private Card card;

    private long summma;

    @CreationTimestamp
    private Timestamp transferTime;
}
