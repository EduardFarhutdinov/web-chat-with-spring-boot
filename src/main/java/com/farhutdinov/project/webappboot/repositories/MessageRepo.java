package com.farhutdinov.project.webappboot.repositories;

import com.farhutdinov.project.webappboot.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message,Long> {
    List<Message> findByTag(String tag);
}
