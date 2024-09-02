package com.egegunes.backend1.Service.Class;


import com.egegunes.backend1.Common.CommonHttpStatus;
import com.egegunes.backend1.Common.GeneralException;
import com.egegunes.backend1.Entity.Users;
import com.egegunes.backend1.Repository.IUsersRepo;
import com.egegunes.backend1.Service.Interface.IUsersService;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.swing.text.Position;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UsersService implements IUsersService {

    private IUsersRepo usersRepo;

    public UsersService(IUsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public List<Users> findByFirstName(String firstName) {
        if(firstName == null || firstName.isEmpty()){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        return usersRepo.findByFirstName(firstName);
    }

    @Override
    public List<Users> findByLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        return usersRepo.findByLastName(lastName);
    }

    @Override
    public List<Users> findByRole(Role role) {
        if (role == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        return usersRepo.findByRole(role);
    }

    @Override
    public List<Users> findByPosition(Position position) {
        if (position == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        return usersRepo.findByPosition(position);
    }

    @Override
    public List<Users> findByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        return usersRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        String hashedPassword = hashPassword(password);

        return usersRepo.existsByPassword(hashedPassword);
    }

    public String hashPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        return usersRepo.existsByPhoneNumber(phoneNumber);
    }


    @Override
    public Users save(Users entity) {
        if (entity.getUserId() == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
            }
        Optional<Users> existingUser = usersRepo.findById(entity.getUserId());
        if (existingUser.isPresent()) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        else{
            return usersRepo.save(entity);
        }
    }

    @Override
    public Users update(Users entity) {
        // Kullanıcının ID'sini kontrol et
        if (entity.getUserId() == null) {
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }

        Optional<Users> existingUser = usersRepo.findById(entity.getUserId());
        // Kullanıcı mevcut değilse, hata fırlat
        if (existingUser.isEmpty()) {
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
        }
        // Kullanıcı mevcutsa, güncellenmiş bilgileri ile kullanıcıyı güncelle
        Users userToUpdate = existingUser.get();
        // Sadece güncellenebilecek alanları kontrol et ve güncelle
        if (entity.getUserName() != null) {
            userToUpdate.setUserName(entity.getUserName());
        }
        if (entity.getFirstName() != null) {
            userToUpdate.setFirstName(entity.getFirstName());
        }
        if (entity.getLastName() != null) {
            userToUpdate.setLastName(entity.getLastName());
        }
        if (entity.getPhoneNumber() != null) {
            userToUpdate.setPhoneNumber(entity.getPhoneNumber());
        }
        if (entity.getPassword() != null) {
            userToUpdate.setPassword(entity.getPassword());
        }
        if (entity.getPosition() != null) {
            userToUpdate.setPosition(entity.getPosition());
        }
        if (entity.getRole() != null) {
            userToUpdate.setRole(entity.getRole());
        }
        // Güncellenmiş kullanıcıyı kaydet
        return usersRepo.save(userToUpdate);
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Users> databaseSavedExistId = usersRepo.findById(id);
        if(databaseSavedExistId.isPresent()){
            usersRepo.deleteById(id);
        }
        else {
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Page<Users> getAll(Pageable pageable) {
      return usersRepo.findAll(pageable);
    }

    @Override
    public List<Users> getAll() {
        return usersRepo.findAll();
    }

    @Override
    public Page<Users> getAllSorting(Pageable pageable, Sort sort) {
        // Sayfalama ve sıralama bilgilerini birleştir
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return usersRepo.findAll(sortedPageable);
    }


}

