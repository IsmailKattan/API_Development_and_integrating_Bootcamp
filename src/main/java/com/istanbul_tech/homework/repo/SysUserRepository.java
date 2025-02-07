package com.istanbul_tech.homework.repo;

import com.istanbul_tech.homework.model.SysUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SysUserRepository extends MongoRepository<SysUser, String>{
    boolean existsByUsername(String username);

    Optional<SysUser> findByUsername(String username);
}
