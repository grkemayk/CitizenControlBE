package com.grkemaykac.citizenService.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "Citizen Return Api model documentation", description = "Model")
public class citizenDto {
    private Long id;
    @NotNull(message = "Citizen situation can not be empty!")
    private Boolean citizen;//For lombok. Lombok generates boolean variable with 'is' keyword.
    @NotNull(message = "Name can not be empty!")
    private String name;
    @NotNull(message = "Driving License situation can not be empty!")
    private Boolean hasDrivingLicense;
    private List<childrenInfoDto> Children = new ArrayList<>();
    private Integer childrenCount;
}
