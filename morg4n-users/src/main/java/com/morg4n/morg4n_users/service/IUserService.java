package com.morg4n.morg4n_users.service;

import com.morg4n.morg4n_users.model.User;

import java.util.List;

/**
 * @author Julian David Camacho Erazo (Morg4n) {@literal <jdacamachoe@gmail.com>}
 */
public interface IUserService {
    public List<User> findAll();
    public User findById(String id) throws Exception;
    public User save(User user);
    public User update(String id, User user) throws  Exception;
    public boolean delete(String id) throws Exception;
}
