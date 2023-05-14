package com.hub.repository;


import com.hub.model.Post;
import com.hub.model.Subhub;
import com.hub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubhub(Subhub subhub);

    List<Post> findByUser(User user);
}
