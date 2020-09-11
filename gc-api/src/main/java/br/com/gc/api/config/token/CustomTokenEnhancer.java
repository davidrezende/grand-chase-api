package br.com.gc.api.config.token;

import br.com.gc.api.model.VoUserSystem;
import br.com.gc.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    public UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        VoUserSystem userSystem = (VoUserSystem) authentication.getPrincipal();
        Map<String, Object> addInfo = new HashMap<>();

        addInfo.put("loginUID", userSystem.getUser().getLoginUID());
        addInfo.put("name", userSystem.getUser().getLogin());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
        return accessToken;
    }

}