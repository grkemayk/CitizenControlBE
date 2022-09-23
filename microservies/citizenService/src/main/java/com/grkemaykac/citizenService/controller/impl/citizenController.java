package com.grkemaykac.citizenService.controller.impl;

import com.grkemaykac.citizenService.controller.ICitizenController;
import com.grkemaykac.citizenService.dto.*;
import com.grkemaykac.citizenService.service.citizenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Citizen Api Doc")
public class citizenController implements ICitizenController {
    @Autowired
    citizenService CitizenService;

    @ApiOperation(value = "Add Citizen Method")
    @Override
    public ResponseEntity<citizenDto> saveCitizen(citizenSaveDto newCitizen) {
        citizenDto savedCitizenDto = CitizenService.saveCitizen(newCitizen);
        if(savedCitizenDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok(savedCitizenDto);
    }
    @ApiOperation(value = "Get Citizen By Id Method")
    @Override
    public ResponseEntity<citizenDto>  getCitizenById(Long id) {
        return ResponseEntity.ok(CitizenService.getById(id));
    }

    @Override
    public ResponseEntity<List<citizenDto>> getCitizenByFilter(citizenFilterDto citizen) {
        return ResponseEntity.ok(CitizenService.getByFilter(citizen));
    }

    @ApiOperation(value = "Update Citizen Method")
    @Override
    public ResponseEntity<citizenDto>  updateCitizen(citizenUpdateDto updateCitizen) {
        return ResponseEntity.ok(CitizenService.updateCitizen(updateCitizen));
    }

    @ApiOperation(value = "Save Child Method")
    @Override
    public ResponseEntity<childrenDto> saveChild(childrenDto newChild) {
        return ResponseEntity.ok(CitizenService.saveChild(newChild));
    }

    @ApiOperation(value = "Get All Records Method")
    @Override
    public ResponseEntity<List<citizenDto>> getAll() {
        return ResponseEntity.ok(CitizenService.getAll());
    }

}
