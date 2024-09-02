package com.egegunes.backend1.Service.Interface;


import com.egegunes.backend1.Entity.Ratings;
import com.egegunes.backend1.Entity.Users;

import java.util.List;

public interface IRatingsService extends IService<Ratings>{


    List<Ratings> findByRatingValue(Integer ratingValue);

    List<Ratings> findByFrom(Users from);

    List<Ratings> findByTo(Users to);

    boolean existsByRatingId(Integer ratingId);
}

