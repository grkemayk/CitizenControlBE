package com.grkemaykac.citizenService.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Citizen Update Api model documentation", description = "Model")
public class citizenUpdateDto {
    private Long id;
    @NotNull(message = "Citizen situation can not be empty!")
    private Boolean citizen;//For lombok. Lombok generates boolean variable with 'is' keyword.
    @NotNull(message = "Name can not be empty!")
    private String name;
    @NotNull(message = "Driving License situation can not be empty!")
    private Boolean hasDrivingLicense;
}
