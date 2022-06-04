package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.GuestTokenEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestTokenRepository extends JpaRepository<GuestTokenEntity,Long> {

    GuestTokenEntity findByToken(String token);

    @EntityGraph(value = "guest-likes")
    @Query("SELECT g FROM GuestTokenEntity g where g.token = :token")
    Optional<GuestTokenEntity> findByTokenGraphLikes(@Param("token") String token);


}
