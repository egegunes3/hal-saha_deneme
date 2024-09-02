package com.egegunes.backend1.Entity;


import com.egegunes.backend1.Entity.Enums.Positions;
import com.egegunes.backend1.Entity.Enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @Column
    private String userName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Positions position;

    @Enumerated(EnumType.STRING)
    @Column
    private Roles role;

    @ManyToOne
    @JoinColumn(name = "organizationId", referencedColumnName = "organizationId")
    private Organizations organization;

}

