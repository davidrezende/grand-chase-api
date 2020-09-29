package br.com.gc.api.service;

import br.com.gc.api.model.CharacterInfo;
import br.com.gc.api.model.VirtualCash;
import br.com.gc.api.repository.CharacterInfoRepository;
import br.com.gc.api.repository.VirtualCashRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoinService {
    public final VirtualCashRepository virtualCashRepository;
    public final CharacterInfoRepository characterInfoRepository;

    public Optional<VirtualCash> getVPInfo(Integer loginUID) throws Exception {
        log.info("Searching VPUser in table of VP Coin for this loginID:: " + loginUID);
        return virtualCashRepository.findByLoginUID(loginUID);
    }

    public VirtualCash sendVPUser(VirtualCash vpUser) throws Exception {
        Optional<VirtualCash> oldVP = getVPInfo(vpUser.getLoginUID());
        oldVP.ifPresent(virtualCash -> vpUser.setVcPoint(vpUser.getVcPoint() + virtualCash.getVcPoint()));

        log.info("Send " + vpUser.getVcPoint() + "VP from User with loginUID:: " + vpUser.getLoginUID());
        return virtualCashRepository.save(vpUser);
    }

    public Optional<List<CharacterInfo>> getGPInfo(Integer loginUID) throws Exception {
        log.info("Searching GPUser entitys for this loginID:: " + loginUID);
        return characterInfoRepository.findByLoginUID(loginUID);
    }

    public List<CharacterInfo> sendGPUser(CharacterInfo gpUser) throws Exception {
        Optional<List<CharacterInfo>> oldGP = getGPInfo(gpUser.getLoginUID());
        log.info("Send " + gpUser.getGamePoint() + "GP from User with loginUID:: " + gpUser.getLoginUID());
        oldGP.get().forEach(characterInfo -> {
            characterInfo.setGamePoint(characterInfo.getGamePoint() + gpUser.getGamePoint());
            characterInfoRepository.save(characterInfo);
        });
        return oldGP.get();
    }


}
