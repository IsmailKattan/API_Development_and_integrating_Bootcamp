package com.istanbul_tech.homework.repo;

import com.istanbul_tech.homework.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
