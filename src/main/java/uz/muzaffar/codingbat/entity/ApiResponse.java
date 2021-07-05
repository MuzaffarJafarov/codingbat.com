package uz.muzaffar.codingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String msg;
    private boolean isSuccess;
    private Object object;

    public ApiResponse(String msg, boolean isSuccess) {
        this.msg = msg;
        this.isSuccess = isSuccess;
    }
}
