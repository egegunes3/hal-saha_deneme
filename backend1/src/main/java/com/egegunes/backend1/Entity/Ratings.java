package com.egegunes.backend1.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingId")
    private Integer ratingId;

    @Column
    private Integer ratingValue;

    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private Games gameId;

    @ManyToOne
    @JoinColumn(name = "from", referencedColumnName = "userId")
    private Users from;

    @ManyToOne
    @JoinColumn(name = "to", referencedColumnName = "userId")
    private Users to;



}