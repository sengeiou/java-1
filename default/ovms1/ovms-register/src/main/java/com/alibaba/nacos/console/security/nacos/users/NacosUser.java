package com.alibaba.nacos.console.security.nacos.users;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.core.auth.User;

/**
 * @author nkorange
 * @since 1.2.0
 */
public class NacosUser extends User {

    private String token;

    private boolean globalAdmin = false;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isGlobalAdmin() {
        return globalAdmin;
    }

    public void setGlobalAdmin(boolean globalAdmin) {
        this.globalAdmin = globalAdmin;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
