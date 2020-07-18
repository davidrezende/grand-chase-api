package br.com.gc.api.controller;

import br.com.gc.api.model.UserItem;
import br.com.gc.api.model.UserItemAttribute;
import br.com.gc.api.model.UserItemSocket;
import br.com.gc.api.model.UserItemStrength;
import br.com.gc.api.repository.UserItemAttributeRepository;
import br.com.gc.api.repository.UserItemRepository;
import br.com.gc.api.repository.UserItemSocketRepository;
import br.com.gc.api.repository.UserItemStrengthRepository;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@RequestMapping("/api/v1/item")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserItemController {
    public final UserItemRepository userItemRepository;
    public final UserItemSocketRepository userItemSocketRepository;
    public final UserItemAttributeRepository userItemAttributeRepository;
    public final UserItemStrengthRepository userItemStrengthRepository;

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItem> newItem (@RequestBody UserItem item){
        log.info("Adding new item ::"+ item.getItemID() + " for login ID::" + item.getLoginUID());
        item.setDelDateA(DateFormatSQLServer.format(new Date()));
        log.info(item.toString());
        return ResponseEntity.ok().body(userItemRepository.save(item));
    }

    @GetMapping(path = "/searchLastItem/item/{itemID}/login/{loginUID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItem> lastItem (@PathVariable Integer itemID, @PathVariable Integer loginUID) throws Exception {
        log.info("Searching last item for this loginID:: "+ loginUID + " and itemID::" + itemID);
        UserItem item = userItemRepository.findFirstByItemIDAndLoginUIDOrderByItemUIDDesc(itemID, loginUID)
                .orElseThrow(() -> new Exception("Last item:: "+ itemID + " added not found for this login :: " + loginUID));
        return ResponseEntity.ok().body(item);
    }

    @PostMapping(path = "/add/card", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItemSocket> addCardItem (@RequestBody UserItemSocket card){
        log.info("Adding cardID::" + card.getCardID() + "in slotID::" + card.getSlotID()
                + " to itemUID::" + card.getItemUID() + " for this loginUID::" + card.getLoginUID());
      //  card.setSocketState(2); // 3 closed slot with card - 2 opened slot without card
        return ResponseEntity.ok().body(userItemSocketRepository.save(card));
    }

    @PostMapping(path = "/add/attribute", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItemAttribute> addAttributeItem (@RequestBody UserItemAttribute attribute){
        log.info("Adding attribute typeID::" + attribute.getTypeID() + " in slotID::" + attribute.getSlotID()
                + " to itemUID::" + attribute.getItemUID() + " for this loginUID::" + attribute.getLoginUID());
        // attribute.setTypeID(-1); // -1 is default for no attribute
        // attribute.setAttributeState(0); // 0 is default for no choose attribute
        return ResponseEntity.ok().body(userItemAttributeRepository.save(attribute));
    }

    @PostMapping(path = "/strengthen", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItemStrength> strengthenItem (@RequestBody UserItemStrength level){
        log.info("Strengthening itemID:: " + level.getEquippedItemUID() + " with stone itemUID::"
                + level.getItemUID() + " for this level::" + level.getStrengthLevel()
                + " and this loginUID::" + level.getLoginUID());
        return ResponseEntity.ok().body(userItemStrengthRepository.save(level));
    }

}
