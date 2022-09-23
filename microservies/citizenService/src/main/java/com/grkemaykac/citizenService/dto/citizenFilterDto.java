package com.grkemaykac.citizenService.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Citizen Filter Api model documentation", description = "Model")
public class citizenFilterDto {
    private Boolean citizen;//For lombok. Lombok generates boolean variable with 'is' keyword.
    private String name;
    private Boolean hasDrivingLicense;
    private Integer childrenCount;
}