package org.springcourse.project.springsocialnetwork.web;

import java.util.List;

import org.springcourse.project.springsocialnetwork.exception.EntityNotFoundException;
import org.springcourse.project.springsocialnetwork.model.Group;
import org.springcourse.project.springsocialnetwork.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService service;

    @GetMapping
    public List<Group> getGroups() {
        return service.getGroups();
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        final Group createdGroup = service.createGroup(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group, @PathVariable("id") final Long id) {
        Group updatedGroup = null;
        try {
            updatedGroup = service.updateGroup(group, id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") final Long id) {
        try {
            service.deleteGroup(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
