package com.grkemaykac.citizenService.repository;

import com.grkemaykac.citizenService.entity.childrenEntity;
import com.grkemaykac.citizenService.entity.citizenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface childrenRepository extends JpaRepository<childrenEntity, Long> {

    //@Query("SELECT childrenEntity.parent FROM childrenEntity c GROUP BY childrenEntity.parent HAVING COUNT(childrenEntity.parent) > 1")
    @Query(value = "SELECT p from childrenEntity c JOIN c.parent p group by p having count(c.parent) >= :childrenCount")
    List<citizenEntity> findByChildrenCount(@Param("childrenCount") Long childrenCount);
    List<childrenEntity> findByParentId(Long parent);
}
