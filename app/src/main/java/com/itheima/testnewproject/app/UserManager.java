package com.itheima.testnewproject.app;


import com.itheima.testnewproject.bean.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/9 10:32 <br/>
 * Version 1.0
 */
@Singleton
public class UserManager {

    private User mUser;

    @Inject
    public UserManager() {
    }

    public boolean isLoggedIn() {
        return mUser != null;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
