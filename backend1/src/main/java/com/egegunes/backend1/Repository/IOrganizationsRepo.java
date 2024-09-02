package com.egegunes.backend1.Repository;


import com.egegunes.backend1.Entity.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrganizationsRepo extends JpaRepository<Organizations, Integer> {



}