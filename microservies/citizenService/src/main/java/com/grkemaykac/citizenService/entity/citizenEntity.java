package com.grkemaykac.citizenService.entity;

import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Table(name = "citizen")
@FilterDef(name="citizen", parameters={
        @ParamDef( name="name", type="String" )
})
@Filters( {
        @Filter(name="nameFilter", condition=":name = name")
} )
@Data
public class citizenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Boolean citizen;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean hasDrivingLicense;



}
