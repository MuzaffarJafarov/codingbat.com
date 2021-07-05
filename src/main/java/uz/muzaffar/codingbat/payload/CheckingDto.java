package uz.muzaffar.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckingDto {
    private String expected;

    private Double run;

    private Integer taskId;

}
