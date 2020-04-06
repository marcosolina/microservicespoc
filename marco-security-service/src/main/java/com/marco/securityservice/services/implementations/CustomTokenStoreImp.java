package com.marco.securityservice.services.implementations;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

import com.marco.securityservice.model.AccessTokenDocument;
import com.marco.securityservice.model.RefreshTokenDocument;
import com.marco.securityservice.repositories.AccessTokenDocumentRepository;
import com.marco.securityservice.repositories.RefreshTokenDocumentRepository;
import com.marco.securityservice.services.interfaces.CustomTokenStoreInt;

public class CustomTokenStoreImp implements CustomTokenStoreInt {

    @Autowired
    private AccessTokenDocumentRepository accessTokenRepo;

    @Autowired
    private RefreshTokenDocumentRepository refreshTokenRepo;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        Optional<AccessTokenDocument> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(token));
        if (accessToken.isPresent()) {
            return accessToken.get().getAuthentication();
        }
        return null;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String refreshToken = null;
        if (token.getRefreshToken() != null) {
            refreshToken = token.getRefreshToken().getValue();
        }

        if (readAccessToken(token.getValue()) != null) {
            removeAccessToken(token);
        }

        AccessTokenDocument atd = new AccessTokenDocument();
        atd.setId(UUID.randomUUID().toString() + UUID.randomUUID().toString());
        atd.setTokenId(extractTokenKey(token.getValue()));
        atd.setToken(token);
        atd.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
        atd.setUsername(authentication.isClientOnly() ? null : authentication.getName());
        atd.setClientId(authentication.getOAuth2Request().getClientId());
        atd.setAuthentication(authentication);
        atd.setRefreshToken(extractTokenKey(refreshToken));

        accessTokenRepo.save(atd);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        Optional<AccessTokenDocument> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(tokenValue));
        if (accessToken.isPresent()) {
            return accessToken.get().getToken();
        }
        return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        Optional<AccessTokenDocument> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(token.getValue()));
        if (accessToken.isPresent()) {
            accessTokenRepo.delete(accessToken.get());
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        RefreshTokenDocument rtd = new RefreshTokenDocument();
        rtd.setId(UUID.randomUUID().toString() + UUID.randomUUID().toString());
        rtd.setTokenId(extractTokenKey(refreshToken.getValue()));
        rtd.setToken(refreshToken);
        rtd.setAuthentication(authentication);

        refreshTokenRepo.save(rtd);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        Optional<RefreshTokenDocument> refreshToken = refreshTokenRepo.findByTokenId(extractTokenKey(tokenValue));
        if (refreshToken.isPresent()) {
            return refreshToken.get().getToken();
        }

        return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        Optional<RefreshTokenDocument> refreshToken = refreshTokenRepo.findByTokenId(extractTokenKey(token.getValue()));
        if (refreshToken.isPresent()) {
            return refreshToken.get().getAuthentication();
        }

        return null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        Optional<RefreshTokenDocument> refreshToken = refreshTokenRepo.findByTokenId(extractTokenKey(token.getValue()));
        if (refreshToken.isPresent()) {
            refreshTokenRepo.delete(refreshToken.get());
        }
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        Optional<AccessTokenDocument> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(refreshToken.getValue()));
        if (accessToken.isPresent()) {
            accessTokenRepo.delete(accessToken.get());
        }

    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);

        Optional<AccessTokenDocument> accessTokenDoc = accessTokenRepo.findByAuthenticationId(authenticationId);
        if (accessTokenDoc.isPresent()) {
            accessToken = accessTokenDoc.get().getToken();
            if (accessToken != null && !authenticationId.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken)))) {
                removeAccessToken(accessToken);
                storeAccessToken(accessToken, authentication);
            }
        }

        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        Collection<OAuth2AccessToken> tokens = new ArrayList<>();

        accessTokenRepo.findByClientIdAndUsername(clientId, userName).stream().forEach(document -> {
            tokens.add(document.getToken());
        });

        return tokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        Collection<OAuth2AccessToken> tokens = new ArrayList<>();

        accessTokenRepo.findByClientId(clientId).stream().forEach(document ->{
            tokens.add(document.getToken());
        });

        return tokens;
    }

    private String extractTokenKey(String value) {
        if (value == null) {
            return null;
        } else {
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var5) {
                throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
            }

            try {
                byte[] e = digest.digest(value.getBytes("UTF-8"));
                return String.format("%032x", new Object[] { new BigInteger(1, e) });
            } catch (UnsupportedEncodingException var4) {
                throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
            }
        }
    }

}
