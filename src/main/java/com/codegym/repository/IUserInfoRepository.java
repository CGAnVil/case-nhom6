package com.codegym.repository;

import com.codegym.model.User;
import com.codegym.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo,Long> {
    Iterable<UserInfo> findAllByUser(User user);
}
