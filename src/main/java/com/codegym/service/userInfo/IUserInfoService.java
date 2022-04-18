package com.codegym.service.userInfo;

import com.codegym.model.User;
import com.codegym.model.UserInfo;
import com.codegym.service.IGeneralService;

public interface IUserInfoService extends IGeneralService<UserInfo> {
    Iterable<UserInfo> findAllByUser(User user);
}
