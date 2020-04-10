package com.marco.securityservice.services.implementations;

import com.marco.securityservice.dto.ApiUser;
import com.marco.securityservice.model.UserDocument;
import com.marco.securityservice.services.interfaces.ApiModelServiceInt;

public class ApiModelServiceImp implements ApiModelServiceInt {

    @Override
    public ApiUser fromUserDocumentToApiUser(UserDocument user) {
        ApiUser u = new ApiUser();
        u.setPassw(user.getPassword());
        u.setUserName(user.getUserName());
        u.setAuthorities(user.getAuthorities());
        return u;
    }

    @Override
    public UserDocument fromApiUserToUserDocument(ApiUser apiUser) {
        UserDocument d = new UserDocument();
        d.setAuthorities(apiUser.getAuthorities());
        d.setPassword(apiUser.getPassw());
        d.setUserName(apiUser.getUserName());
        return d;
    }

}
