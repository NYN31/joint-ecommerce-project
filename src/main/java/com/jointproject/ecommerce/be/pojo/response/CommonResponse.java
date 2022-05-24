package com.jointproject.ecommerce.be.pojo.response;

import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private Integer code;
    private ResultStatus resultStatus;
}
