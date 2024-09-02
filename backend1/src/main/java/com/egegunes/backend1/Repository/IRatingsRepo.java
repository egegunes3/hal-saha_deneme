package com.egegunes.backend1.Repository;


import com.egegunes.backend1.Entity.Games;
import com.egegunes.backend1.Entity.Ratings;
import com.egegunes.backend1.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRatingsRepo  extends JpaRepository<Ratings, Integer> {


    List<Ratings> findByRatingValue(Integer ratingValue);

    List<Ratings> findByFrom(Users from);

    List<Ratings> findByTo(Users to);

    boolean existsByRatingId(Integer ratingId);

    List<Ratings> findByGameId(Games game);






}