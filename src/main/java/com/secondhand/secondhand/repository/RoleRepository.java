package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.RoleEntity;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    RoleEntity findByRole(RoleEnum roleEnum);
}
