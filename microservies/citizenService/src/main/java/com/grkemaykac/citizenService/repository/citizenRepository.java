package com.grkemaykac.citizenService.repository;

import com.grkemaykac.citizenService.entity.citizenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface citizenRepository extends JpaRepository<citizenEntity, Long>{
    List<citizenEntity> findByCitizen(Boolean citizen);
    List<citizenEntity> findByName(String name);
    List<citizenEntity> findByHasDrivingLicense(Boolean hasDrivingLicense);
}
