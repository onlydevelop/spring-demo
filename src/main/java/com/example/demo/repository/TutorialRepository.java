package com.example.demo.repository;

import com.example.demo.model.Tutorial;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TutorialRepository extends CrudRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
