package com.jointproject.ecommerce.be.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {
    private Integer value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
