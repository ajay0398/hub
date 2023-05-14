package com.hub.repository;

import com.hub.model.SubHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubHubRepository extends JpaRepository<SubHub, Long> {
}
