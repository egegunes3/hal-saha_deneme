package com.egegunes.backend1.Service.Interface;


import com.egegunes.backend1.Entity.Games;

import java.util.List;

public interface IGamesService extends IService<Games>{

    List<Games> countByAwayScore(Integer awayScore);

    List<Games> countByHomeScore(Integer homeScore);
}