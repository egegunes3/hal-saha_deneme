package com.egegunes.backend1.Service.Class;


import com.egegunes.backend1.Common.CommonHttpStatus;
import com.egegunes.backend1.Common.GeneralException;
import com.egegunes.backend1.Entity.Games;
import com.egegunes.backend1.Entity.Organizations;
import com.egegunes.backend1.Entity.Users;
import com.egegunes.backend1.Repository.IOrganizationsRepo;
import com.egegunes.backend1.Repository.IUsersRepo;
import com.egegunes.backend1.Service.Interface.IOrganizationsService;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.egegunes.backend1.Entity.Enums.Roles;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@NoArgsConstructor
public class OrganizationsService implements IOrganizationsService {

    private IOrganizationsRepo organizationsRepo;
    private IUsersRepo usersRepo;

    public OrganizationsService(IOrganizationsRepo organizationsRepo) {
        this.organizationsRepo = organizationsRepo;
    }


    public Organizations save(Organizations entity, Integer userId) {

        checkUserRole(userId);

        if (entity.getOrganizationId() == null){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Organizations> savedGameId = organizationsRepo.findById(entity.getOrganizationId());

        if (savedGameId.isPresent()){
            throw new GeneralException((CommonHttpStatus.BAD_REQUEST));
        }
        else
            return organizationsRepo.save(entity);
    }

    public Users checkUserRole(Integer userId){
        Optional<Users> usersOptional = usersRepo.findById(userId);
        if (usersOptional.isEmpty()){
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();
        if (user.getRole() != Roles.OWNER){
            throw new GeneralException(CommonHttpStatus.FORBIDDEN);
        }
        else
            return user;

    }

    @Override
    public Organizations save(Organizations entity) {
        return null;
    }

    @Override
    public Organizations update(Organizations entity) {
        if(entity.getOrganizationId() == null){
            throw new GeneralException((CommonHttpStatus.BAD_REQUEST));
        }
        Optional<Organizations> existingOrganization = organizationsRepo.findById(entity.getOrganizationId());
         if (existingOrganization.isEmpty()){
             throw new GeneralException((CommonHttpStatus.NOT_FOUND));
         }
         Organizations organizationsToUpdate = existingOrganization.get();

        if (entity.getOrganizationId() != null) {
            organizationsToUpdate.setOrganizationId(entity.getOrganizationId());
        }
        if (entity.getOrganizationName() != null) {
            organizationsToUpdate.setOrganizationName(entity.getOrganizationName());
        }
        if (entity.getOrganizationOwner() != null) {
            organizationsToUpdate.setOrganizationOwner(entity.getOrganizationOwner());
        }
        if (entity.getMemberCount() != null) {
            organizationsToUpdate.setMemberCount(entity.getMemberCount());
        }
        return organizationsRepo.save(organizationsToUpdate);
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new GeneralException(CommonHttpStatus.BAD_REQUEST);
        }
        Optional<Organizations> databaseSavedExistId = organizationsRepo.findById(id);
        if(databaseSavedExistId.isPresent()){
            organizationsRepo.deleteById(id);
        }
        else {
            throw new GeneralException(CommonHttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Page<Organizations> getAll(Pageable pageable) {
        return organizationsRepo.findAll(pageable);
    }

    @Override
    public List<Organizations> getAll() {
        return organizationsRepo.findAll();
    }

    @Override
    public Page<Organizations> getAllSorting(Pageable pageable, Sort sort) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return organizationsRepo.findAll(sortedPageable);
    }
}

