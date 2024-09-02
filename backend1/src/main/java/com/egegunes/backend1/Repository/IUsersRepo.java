package com.egegunes.backend1.Repository;


import com.egegunes.backend1.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import javax.swing.text.Position;

import java.util.List;

@Repository
public interface IUsersRepo extends JpaRepository<Users, Integer> {

    //create delete update read

    List<Users> findByFirstName(String firstName);

    List<Users> findByLastName(String lastName);

    List<Users> findByPosition(Position position);

    List<Users> findByRole(Role role);

    List<Users> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPassword(String hashedPassword);




}
