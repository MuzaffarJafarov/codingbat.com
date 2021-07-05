package uz.muzaffar.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressDto {
    private Boolean isPassed;
    private Boolean isStar;

    private Integer chance;
    private Integer taskId;
    private Integer userId;


}
