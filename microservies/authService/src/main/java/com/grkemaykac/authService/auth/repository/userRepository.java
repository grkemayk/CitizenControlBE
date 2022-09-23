package com.grkemaykac.authService.auth.repository;

import com.grkemaykac.authService.auth.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface userRepository extends JpaRepository<userEntity, Long> {

    userEntity findByUsername(String username);
    userEntity findByEmail(String email);
}
