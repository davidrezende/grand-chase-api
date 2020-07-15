package br.com.gc.api.controller;

import br.com.gc.api.model.UserItem;
import br.com.gc.api.repository.UserItemRepository;
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
        log.info(item.toString());
        item.setDelDateA(new Date());
        return ResponseEntity.ok().body(userItemRepository.save(item));
    }

}
