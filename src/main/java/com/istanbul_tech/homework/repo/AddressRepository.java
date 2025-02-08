package com.istanbul_tech.homework.repo;

import com.istanbul_tech.homework.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
    List<Address> findByUserId(String id);
}
