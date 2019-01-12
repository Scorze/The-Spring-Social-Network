package org.springcourse.project.springsocialnetwork.service;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.dao.GroupRepository;
import org.springcourse.project.springsocialnetwork.exception.EntityNotFoundException;
import org.springcourse.project.springsocialnetwork.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository repository;

    @Override
    public List<Group> getGroups() {
        return repository.findAll();
    }

    @Override
    public Group createGroup(Group group) {
        return repository.save(group);
    }

    @Override
    public Group updateGroup(Group group, final long id) {
        Optional<Group> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("Group with id '%d' doesn't exist.", id));
        }
        Group existingGroup = existing.get();
        existingGroup.setName(group.getName());
        return repository.save(existingGroup);
    }

    @Override
    public void deleteGroup(long id) {
        Optional<Group> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("Group with id '%d' doesn't exist.", id));
        }
        Group existingGroup = existing.get();
        repository.delete(existingGroup);
    }

}
