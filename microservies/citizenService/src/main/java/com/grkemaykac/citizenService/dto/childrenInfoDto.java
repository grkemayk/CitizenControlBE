package com.grkemaykac.citizenService.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Children Info Api model documentation", description = "Model")
public class childrenInfoDto {
    private String name;
    private Long id;
}
