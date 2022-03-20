package com.enes.user.service;

import com.enes.user.entity.User;

public interface IUserService {
    User create(User user);

    User findByUsername(String username);

    void delete(long id);
}
