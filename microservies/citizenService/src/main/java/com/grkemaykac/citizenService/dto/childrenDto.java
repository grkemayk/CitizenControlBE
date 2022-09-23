package com.grkemaykac.citizenService.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Children Register Api model documentation", description = "Model")
public class childrenDto {
    private Long parentId;
    private Long childId;
}
