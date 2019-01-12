package org.springcourse.project.springsocialnetwork.service;

import java.util.List;

import org.springcourse.project.springsocialnetwork.model.Group;

public interface GroupService {

    public List<Group> getGroups();

    public Group createGroup(final Group group);

    public Group updateGroup(Group group, final long id);

    public void deleteGroup(long id);
}
