package com.grkemaykac.citizenService.service;

import com.grkemaykac.citizenService.dto.*;
import com.grkemaykac.citizenService.entity.childrenEntity;
import com.grkemaykac.citizenService.entity.citizenEntity;
import com.grkemaykac.citizenService.repository.childrenRepository;
import com.grkemaykac.citizenService.repository.citizenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class citizenService {
    @Autowired
    citizenRepository CitizenRepository;

    @Autowired
    childrenRepository ChildrenRepository;

    @Transactional
    public List<citizenDto> getAll()
    {
        List<citizenEntity> citizenEntityList = CitizenRepository.findAll();
        List<citizenDto> citizenDtoList = new ArrayList<>();
        for(citizenEntity citizen : citizenEntityList)
        {
            citizenDto citizenDtoNew = new citizenDto();
            BeanUtils.copyProperties(citizen, citizenDtoNew);
            setChildren(citizenDtoNew);
            citizenDtoList.add(citizenDtoNew);
        }
        return citizenDtoList;
    }
    @Transactional
    public citizenDto saveCitizen(citizenSaveDto newCitizenSave)
    {
        citizenDto newCitizen = new citizenDto();
        BeanUtils.copyProperties(newCitizenSave, newCitizen);
        if(newCitizen.getId() == null)
        {
            citizenEntity newCitizenEntity = new citizenEntity();
            BeanUtils.copyProperties(newCitizen, newCitizenEntity);
            System.out.println(newCitizenEntity.toString());
            newCitizen.setId(CitizenRepository.save(newCitizenEntity).getId());
            setChildren(newCitizen);
            return newCitizen;
        }
        else
            return null;
    }

    @Transactional
    public citizenDto updateCitizen(citizenUpdateDto updateCitizenDto)
    {
        citizenDto updateCitizen = new citizenDto();
        BeanUtils.copyProperties(updateCitizenDto, updateCitizen);
        CitizenRepository.findById(updateCitizen.getId()).get();
        citizenEntity updateCitizenEntity = CitizenRepository.findById(updateCitizen.getId()).get();
        BeanUtils.copyProperties(updateCitizen, updateCitizenEntity);
        CitizenRepository.save(updateCitizenEntity);
        setChildren(updateCitizen);
        return updateCitizen;
    }


    @Transactional
    public citizenDto getById(Long id)
    {
        citizenDto getCitizen = new citizenDto();
        BeanUtils.copyProperties(CitizenRepository.findById(id).get(), getCitizen);
        setChildren(getCitizen);
        getCitizen.setId(id);
        return getCitizen;
    }
    @Transactional
    public List<citizenDto> getByFilter(citizenFilterDto CitizenFilterDto)//Specifications is better choose
    {
        citizenDto CitizenDto = new citizenDto();
        BeanUtils.copyProperties(CitizenFilterDto, CitizenDto);
        if(CitizenDto.getName() != null)
        {
            return convertCitizenEntityListToDtoList(CitizenRepository.findByName(CitizenDto.getName()));
        }
        else if(CitizenDto.getCitizen() != null)
        {
            return convertCitizenEntityListToDtoList(CitizenRepository.findByCitizen(CitizenDto.getCitizen()));
        }
        else if(CitizenDto.getHasDrivingLicense() != null)
        {
            return convertCitizenEntityListToDtoList(CitizenRepository.findByHasDrivingLicense(CitizenDto.getHasDrivingLicense()));
        }
        else if(CitizenDto.getChildrenCount() != null)
        {
            return convertCitizenEntityListToDtoList(ChildrenRepository.findByChildrenCount(Long.valueOf(CitizenDto.getChildrenCount())));
        }
        return new ArrayList<>();
    }
    @Transactional
    public childrenDto saveChild(childrenDto newChild)
    {
        childrenEntity newChildEntity = new childrenEntity();
        citizenEntity parent = CitizenRepository.findById(newChild.getParentId()).get();
        citizenEntity child = CitizenRepository.findById(newChild.getChildId()).get();
        newChildEntity.setParent(parent);
        newChildEntity.setChild(child);
        ChildrenRepository.save(newChildEntity);
        return newChild;
    }

    private  void setChildren(citizenDto citizen)
    {
        List<childrenEntity> childrenList = ChildrenRepository.findByParentId(citizen.getId());
        if(childrenList.size() > 0)
        {
            List<childrenInfoDto> childrenListDto = new ArrayList<>();
            for(childrenEntity child : childrenList)//Lambda is preferable.
            {
                childrenInfoDto newChildDto = new childrenInfoDto();
                newChildDto.setName(child.getChild().getName());
                newChildDto.setId(child.getChild().getId());
                childrenListDto.add(newChildDto);
            }
            citizen.setChildren(childrenListDto);


        }
        citizen.setChildrenCount(citizen.getChildren().size());
    }


    private List<citizenDto> convertCitizenEntityListToDtoList(List<citizenEntity> entityList)
    {
        List<citizenDto> citizenDtoList = new ArrayList<>();
        for(citizenEntity newEntity : entityList)
        {
            citizenDto CitizenDto = new citizenDto();
            BeanUtils.copyProperties(newEntity, CitizenDto);
            setChildren(CitizenDto);
            citizenDtoList.add(CitizenDto);
        }
        return citizenDtoList;
    }






}
