package br.com.gc.api.controller;

import br.com.gc.api.constants.GlobalConstants;
import br.com.gc.api.model.*;
import br.com.gc.api.service.CharacterService;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;


@Slf4j
@CrossOrigin
@RequestMapping("/api/v1/character")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterController {
    public final CharacterService characterService;

    @GetMapping(path = "/findCharacters/loginUID/{loginUID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ArrayList<Characters>> newItemPanel(@PathVariable Integer loginUID) throws Exception {
        log.info("Call service api/v1/findCharacters " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(characterService.getCharacters(loginUID).get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/findCharacters " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Characters> updatePromotionAndEXP(@RequestBody Characters character)
            throws Exception {
        log.info("Updating character with loginUID::" + character.getLoginUID() + " ; CharType::"+ character.getCharType());

        Characters findedCharacter = characterService.getCharacter(character.getLoginUID(), character.getCharType()).get();
        log.info("Updating finded character::" + findedCharacter.toString());

        if(character.getPromotion() != null
                && (character.getPromotion() >= 0 || character.getPromotion() <= 3 )){
            findedCharacter.setPromotion(character.getPromotion());
        }

        if(character.getExps4() != null
                && (character.getExps4() > 0 || character.getExps4() <= 999999 )){
            findedCharacter.setExps4(character.getExps4());
        }

        log.info("New character for update:: " + findedCharacter.toString());
        return ResponseEntity.ok().body(characterService.updateCharacter(findedCharacter));
    }

}
