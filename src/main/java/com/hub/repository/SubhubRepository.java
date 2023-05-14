package com.hub.repository;


import com.hub.model.Subhub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubhubRepository extends JpaRepository<Subhub, Long> {

    Optional<Subhub> findByName(String subhubName);
}
