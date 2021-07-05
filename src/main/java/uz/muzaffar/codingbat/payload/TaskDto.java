package uz.muzaffar.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String name;

    private String description;

    private String level;

    private Integer topicId;

}
