package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.GuestTokenEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

    @EntityGraph(value = "user-roles")
    @Query("SELECT u FROM UserEntity u where u.email = :email")
    Optional<UserEntity> findByEmailGraph(@Param("email") String email);

    @EntityGraph(value = "user-likes")
    @Query("SELECT u FROM UserEntity u where u.email = :email")
    Optional<UserEntity> findByEmailGraphLikes(@Param("email") String email);

    @EntityGraph(value = "user-addresses")
    @Query("SELECT u FROM UserEntity u where u.email = :email")
    Optional<UserEntity> findByEmailGraphAddress(@Param("email") String email);

    @EntityGraph(value = "speedyAddresses")
    @Query("SELECT u FROM UserEntity u where u.email = :email")
    Optional<UserEntity> findByEmailGraphSpeedyAddresses(@Param("email") String email);

    @EntityGraph(value = "speedyAddressesCityAddress")
    @Query("SELECT u FROM UserEntity u where u.email = :email")
    Optional<UserEntity> findByEmailGraphSpeedyAddressesCityAddress(@Param("email") String email);


}
