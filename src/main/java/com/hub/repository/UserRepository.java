package com.hub.repository;

import com.hub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);
}
