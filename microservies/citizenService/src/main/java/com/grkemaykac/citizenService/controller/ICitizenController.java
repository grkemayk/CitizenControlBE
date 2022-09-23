package com.grkemaykac.citizenService.controller;

import com.grkemaykac.citizenService.dto.*;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/citizen")
@Api(value = "Citizen Api Doc")
public interface ICitizenController {
    @PostMapping("/saveCitizen")
    ResponseEntity<citizenDto> saveCitizen(@RequestBody citizenSaveDto newCitizen);

    @PutMapping("/updateCitizen")
    ResponseEntity<citizenDto>  updateCitizen(@RequestBody @Valid citizenUpdateDto updateCitizen);
    @PostMapping("/saveChildren")
    ResponseEntity<childrenDto> saveChild(@RequestBody @Valid childrenDto newChild);
    @GetMapping("/getAll")
    ResponseEntity<List<citizenDto>> getAll();
    @GetMapping("/getCitizen/{id}")
    ResponseEntity<citizenDto>  getCitizenById(@PathVariable(name = "id") Long id);
    @GetMapping("/getCitizen")
    ResponseEntity<List<citizenDto>>  getCitizenByFilter(citizenFilterDto citizen);

}
