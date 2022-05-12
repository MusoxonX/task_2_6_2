package uz.pdp.task_2_6_2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankomatDto {
    private String kartaTuri;
    private Integer bankId;
    private List<Integer> kupyuraListId;
    private String address;
}
