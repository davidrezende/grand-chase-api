package br.com.gc.api.controller;

import br.com.gc.api.model.UserItem;
import br.com.gc.api.repository.UserItemRepository;
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

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItem> newItem (@RequestBody UserItem item){
        log.info("Add new item ::"+ item.getItemID() + " for login ID::" + item.getLoginUID());
        item.setDelDateA(DateFormatSQLServer.format(new Date()));
        log.info(item.toString());
        return ResponseEntity.ok().body(userItemRepository.save(item));
    }

    @GetMapping(path = "/searchLastItem/item/{itemID}/login/{loginUID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItem> lastItem (@PathVariable Integer itemID, @PathVariable Integer loginUID) throws Exception {
        log.info("Search last item for this loginID:: "+ loginUID + " and itemID::" + itemID);
        UserItem item = userItemRepository.findFirstByItemIDAndLoginUIDOrderByItemUIDDesc(itemID, loginUID)
                .orElseThrow(() -> new Exception("Last item:: "+ itemID + " added not found for this login :: " + loginUID));
        return ResponseEntity.ok().body(item);
    }

}
