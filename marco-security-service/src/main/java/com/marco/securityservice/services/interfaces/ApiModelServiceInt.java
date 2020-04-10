package com.marco.securityservice.services.interfaces;

import com.marco.securityservice.dto.ApiUser;
import com.marco.securityservice.model.UserDocument;

/**
 * This interface provides the necessary functionalities to convert a DB
 * document into the RESP API (or vice versa) info that need to be exchanged.
 * 
 * @author msolina
 *
 */
public interface ApiModelServiceInt {
    /**
     * It converts a {@link UserDocument} into a rest {@link ApiUser}
     * 
     * @param user
     * @return
     */
    public ApiUser fromUserDocumentToApiUser(UserDocument user);

    /**
     * It converts a rest {@link ApiUser} into an {@link UserDocument}
     * 
     * @param apiUser
     * @return
     */
    public UserDocument fromApiUserToUserDocument(ApiUser apiUser);
}
