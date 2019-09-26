package com.farhutdinov.project.webappboot.repositories;

import com.farhutdinov.project.webappboot.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String userName);
}
