package com.egegunes.backend1.Service.Class;


import com.egegunes.backend1.Common.CommonHttpStatus;
import com.egegunes.backend1.Common.GeneralException;
import com.egegunes.backend1.Entity.Games;
import com.egegunes.backend1.Entity.Ratings;
import com.egegunes.backend1.Entity.Users;
import com.egegunes.backend1.Repository.IGamesRepo;
import com.egegunes.backend1.Repository.IRatingsRepo;
import com.egegunes.backend1.Service.Interface.IRatingsService;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class RatingsService implements IRatingsService {

    private IRatingsRepo ratingsRepo;
    private IGamesRepo gamesRepo;


    public RatingsService(IRatingsRepo ratingsRepo) {
        this.ratingsRepo = ratingsRepo;
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Ratings> databaseSavedExistId = ratingsRepo.findById(id);
        if(databaseSavedExistId.isPresent()){
            ratingsRepo.deleteById(id);
        }
        else {
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Ratings save(Ratings entity) {
        if (entity.getRatingId() == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Ratings> existingUser = ratingsRepo.findById(entity.getRatingId());
        if (existingUser.isPresent()) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        else{
            return ratingsRepo.save(entity);
        }
    }

    @Override
    public Ratings update(Ratings entity) {
        if (entity.getRatingId() == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }

        Optional<Ratings> existingRating = ratingsRepo.findById(entity.getRatingId());
        if (existingRating.isEmpty()) {
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
        }
        Ratings ratingsToUpdate = existingRating.get();
        if (entity.getRatingValue() != null) {
            ratingsToUpdate.setRatingId(entity.getRatingId());
        }

        return ratingsRepo.save(ratingsToUpdate);
    }

    @Override
    public List<Ratings> findByTo(Users to) {
        return ratingsRepo.findByTo(to);
    }

    @Override
    public List<Ratings> findByFrom(Users from) {
        return ratingsRepo.findByFrom(from);
    }

    @Override
    public List<Ratings> findByRatingValue(Integer ratingValue) {
        return ratingsRepo.findByRatingValue(ratingValue);
    }

    @Override
    public Page<Ratings> getAll(Pageable pageable) {
        return ratingsRepo.findAll(pageable);
    }

    @Override
    public Page<Ratings> getAllSorting(Pageable pageable, Sort sort) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return ratingsRepo.findAll(sortedPageable);
    }

    @Override
    public boolean existsByRatingId(Integer ratingId) {
        return false;
    }

    @Override
    public List<Ratings> getAll() {
        return ratingsRepo.findAll();
    }

    public Map<Integer, Double> calculateGameRatings(Integer gameId) {
        // Retrieve the Games entity by ID
        Games game = gamesRepo.findById(gameId)
                .orElseThrow(() -> new GeneralException(CommonHttpStatus.NOT_FOUND));

        // Get all ratings associated with the Games entity
        List<Ratings> ratingsForGame = ratingsRepo.findByGameId(game);

        // If no ratings, return an empty map
        if (ratingsForGame.isEmpty()) {
            return Map.of();
        }

        // Calculate average ratings per user
        return ratingsForGame.stream()
                .collect(Collectors.groupingBy(
                        rating -> rating.getTo().getUserId(), // Group by user ID
                        Collectors.averagingDouble(Ratings::getRatingValue) // Calculate average rating
                ));
    }


}



