package com.egegunes.backend1.Service.Class;


import com.egegunes.backend1.Common.CommonHttpStatus;
import com.egegunes.backend1.Common.GeneralException;
import com.egegunes.backend1.Entity.Games;
import com.egegunes.backend1.Entity.Users;
import com.egegunes.backend1.Repository.IGamesRepo;
import com.egegunes.backend1.Repository.IUsersRepo;
import com.egegunes.backend1.Service.Interface.IGamesService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class GamesService implements IGamesService {



    private IGamesRepo gamesRepo;

    @Autowired
    public GamesService(IGamesRepo gamesRepo) {
        this.gamesRepo = gamesRepo;
    }

    @Override
    public List<Games> countByHomeScore(Integer homeScore) {
        return List.of();
    }

    @Override
    public List<Games> countByAwayScore(Integer awayScore) {
        return List.of();
    }

    @Override
    public Games save(Games entity) {
        if (entity.getGameId() == null){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Games> savedGameId = gamesRepo.findById(entity.getGameId());

        if (savedGameId.isPresent()){
            throw new GeneralException((CommonHttpStatus.BAD_REQUEST));
        }
        else
            return gamesRepo.save(entity);
    }

    @Override
    public Games update(Games entity) {

        if (entity.getGameId() == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Games> existingGame = gamesRepo.findById(entity.getGameId());
        if (existingGame.isEmpty()) {
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
        }
        Games gameToUpdate = existingGame.get();

        if (entity.getGameId() != null) {
            gameToUpdate.setGameId(entity.getGameId());
        }
        if (entity.getHomeScore() != null) {
            gameToUpdate.setHomeScore(entity.getHomeScore());
        }
        if (entity.getAwayScore() != null) {
            gameToUpdate.setAwayScore(entity.getAwayScore());
        }
        if (entity.getSquad() != null) {
            gameToUpdate.setSquad(entity.getSquad());
        }
        // Güncellenmiş kullanıcıyı kaydet
        return gamesRepo.save(gameToUpdate);
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Games> savedGameId = gamesRepo.findById(id);
        if(savedGameId.isPresent()){
            gamesRepo.deleteById(id);
        }
        else
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
    }

    @Override
    public Page<Games> getAll(Pageable pageable) {
        return gamesRepo.findAll(pageable);
    }

    @Override
    public List<Games> getAll() {
        return gamesRepo.findAll();
    }

    @Override
    public Page<Games> getAllSorting(Pageable pageable, Sort sort) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return gamesRepo.findAll(sortedPageable);
    }
}

