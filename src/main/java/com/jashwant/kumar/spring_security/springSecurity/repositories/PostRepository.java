package com.jashwant.kumar.spring_security.springSecurity.repositories;

import com.jashwant.kumar.spring_security.springSecurity.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
