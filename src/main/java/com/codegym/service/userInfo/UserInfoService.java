package com.codegym.service.userInfo;

import com.codegym.model.User;
import com.codegym.model.UserInfo;
import com.codegym.repository.IUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements IUserInfoService{
    @Autowired
    private IUserInfoRepository userInfoRepository;
    @Override
    public Iterable<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return userInfoRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public Iterable<UserInfo> findAllByUser(User user) {
        return userInfoRepository.findAllByUser(user);
    }
}
