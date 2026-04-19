package com.trust.microservices.repository;

import com.trust.microservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query(value = "select * from user_t where user_id in (:id)",nativeQuery = true)
    Optional<List<User>> findAllById(List<Long> id);

    UserDetails findByUserName(String username);

    User findByEmail(String email);
}
