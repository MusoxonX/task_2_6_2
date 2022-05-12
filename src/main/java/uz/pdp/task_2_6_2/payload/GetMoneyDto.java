package uz.pdp.task_2_6_2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMoneyDto {
    private Integer cardId;
    private long summa;
}
