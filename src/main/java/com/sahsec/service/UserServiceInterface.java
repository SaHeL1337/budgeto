package com.sahsec.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.sahsec.DTO.UserDto;
import com.sahsec.entities.User;
import com.sahsec.exception.EmailExistsException;
import com.sahsec.exception.UserAlreadyExistException;


public interface UserServiceInterface {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    User findUserByEmail(String email);

    User getUserByID(long id);

}