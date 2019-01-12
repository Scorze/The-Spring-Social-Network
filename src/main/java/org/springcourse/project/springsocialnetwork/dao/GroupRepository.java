package org.springcourse.project.springsocialnetwork.dao;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.model.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
    public List<Group> findAll();
    public Optional<Group> findByName(String name);
}
