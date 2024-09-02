package com.egegunes.backend1.Service.Interface;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IService <T>{

    T save (T entity);

    T update(T entity);

    List<T> getAll();

    Page<T> getAll(Pageable pageable);

    Page<T> getAllSorting(Pageable pageable, Sort sort);

    void delete(Integer id);
}
