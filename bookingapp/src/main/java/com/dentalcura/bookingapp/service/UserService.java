package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter @Setter
@Service
public class UserService {
    private IDao<User> userIDao;


    public void createTableUser(){
        userIDao.createTable();
    }

    public User insertUser(User user){
        return userIDao.insert(user);
    }

    public List<User> selectAllUser(){
        return userIDao.selectAll();
    }

    public User selectUserByID(int id){
        return userIDao.selectByID(id);
    }

    public User updateUserByID(User user){
        return userIDao.updateByID(user);
    }

    public User deleteUserByID(int id){
        return userIDao.deleteByID(id);
    }
}
