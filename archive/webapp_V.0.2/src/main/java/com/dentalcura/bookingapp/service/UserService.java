package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class UserService {
<<<<<<< HEAD
<<<<<<< HEAD

    private final IDao<User> userIDao;

    @Autowired
    public UserService(@Qualifier("userDAOH2") IDao<User> userIDao) {
        this.userIDao = userIDao;
    }

    public void createTableUser(){
        userIDao.createTable();
=======
=======
>>>>>>> main

    private IDao<User> userIDao;

    @Autowired
    public UserService(@Qualifier("userDAOH2") IDao<User> userIDao) {
        this.userIDao = userIDao;
<<<<<<< HEAD
>>>>>>> c8b40bcb99acdaa4ee9d42c0fe2d820099888d0e
=======
>>>>>>> main
    }

    public User insertUser(User user){
        return userIDao.insert(user);
    }

    public List<User> selectAllUser(){
        return userIDao.selectAll();
    }

    public User selectUserByID(Long id){
        return userIDao.selectByID(id);
    }

    public User updateUserByID(User user){
        return userIDao.updateByID(user);
    }

    public User deleteUserByID(Long id){
        return userIDao.deleteByID(id);
    }
}
