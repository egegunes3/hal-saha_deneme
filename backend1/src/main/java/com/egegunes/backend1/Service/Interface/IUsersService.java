package com.egegunes.backend1.Service.Interface;


import com.egegunes.backend1.Entity.Users;

import javax.management.relation.Role;
import javax.swing.text.Position;
import java.util.List;

public interface IUsersService extends IService<Users>{

    List<Users> findByFirstName(String firstName);

    List<Users> findByLastName(String lastName);

    List<Users> findByPosition(Position position);

    List<Users> findByRole(Role role);

    List<Users> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPassword(String password);


}
