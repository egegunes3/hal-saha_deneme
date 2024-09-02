package com.egegunes.backend1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "organizations")
@Entity
public class Organizations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizationId")
    private Integer organizationId;

    @Column
    private String organizationName;

    @Column
    private Integer memberCount;

    @Column
    private String organizationOwner;



}