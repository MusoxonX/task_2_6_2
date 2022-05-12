package uz.pdp.task_2_6_2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private Integer maxsusRaqam;

    private long summa;

    private Integer bankId;

    @Size(min = 3,max = 3)
    private short cvv;

    private String fullName;

    private Date amalQilishMuddati;

    @NotNull
    private String password;

    private String kartaTuri;

}
