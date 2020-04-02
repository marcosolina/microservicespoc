package com.marco.securityservice.services.interfaces;

import com.marco.securityservice.dto.ApiUser;
import com.marco.securityservice.model.UserDocument;

public interface ApiModelServiceInt {
    public ApiUser fromUserDocumentToApiUser(UserDocument user);

    public UserDocument fromApiUserToUserDocument(ApiUser apiUser);

}
