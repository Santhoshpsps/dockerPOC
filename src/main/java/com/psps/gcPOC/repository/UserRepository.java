package com.psps.gcPOC.repository;

import com.psps.gcPOC.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.Optional;

public interface UserRepository extends CassandraRepository<User, String> {
    Optional<User> findByEmail(String email);
}