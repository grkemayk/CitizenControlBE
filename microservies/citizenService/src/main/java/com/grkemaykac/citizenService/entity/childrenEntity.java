package com.grkemaykac.citizenService.entity;

import lombok.Data;

import javax.persistence.*;

//@Entity
@Data
@Entity
@Table(name = "children")
public class childrenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "parent", referencedColumnName = "id", nullable = false)
    private citizenEntity parent;
    @OneToOne
    @JoinColumn(name = "child", referencedColumnName = "id", nullable = false, unique = true)
    private citizenEntity child;

}
