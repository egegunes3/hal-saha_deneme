package com.egegunes.backend1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "games")
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId")
    private Integer gameId;

    @Column
    private Integer homeScore;

    @Column
    private Integer awayScore;

    @Column
    private String squad;

    @ManyToOne
    @JoinColumn(name = "homeOrganizationId", referencedColumnName = "organizationId")
    private Organizations homeOrganization;

    @ManyToOne
    @JoinColumn(name = "awayOrganizationId", referencedColumnName = "organizationId")
    private Organizations awayOrganization;
}
