package com.farhutdinov.project.webappboot.repositories;

import com.farhutdinov.project.webappboot.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message,Long> {
    List<Message> findByTag(String tag);
}
