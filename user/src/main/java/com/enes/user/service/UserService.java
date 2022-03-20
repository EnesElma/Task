package com.enes.user.service;

import com.enes.user.entity.User;
import com.enes.user.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository repository;

    @Override
    public User create(User user){
        return repository.save(user);
    }

    @Override
    public User findByUsername(String username){
        User user = repository.findByUsername(username);
        System.out.println(user.getUsername());
        return user;
    }

    @Override
    public void delete(long id){
        repository.deleteById(id);
    }
}
