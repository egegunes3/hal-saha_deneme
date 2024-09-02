package com.egegunes.backend1.Repository;


import com.egegunes.backend1.Entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IGamesRepo extends JpaRepository<Games, Integer> {


    List<Games> countByAwayScore(Integer awayScore);

    List<Games> countByHomeScore(Integer homeScore);

    Optional<Games> findById(Integer id);

}
