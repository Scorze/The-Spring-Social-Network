package org.springcourse.project.springsocialnetwork.service;

public interface SecurityService {

    public String getLoggedInName();

    public void login(String name, String password);
}
