package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.GuestTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestTokenRepository extends JpaRepository<GuestTokenEntity,Long> {
}
