package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.SpeedyAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedyAddressRepository extends JpaRepository<SpeedyAddressEntity,Long> {
}
