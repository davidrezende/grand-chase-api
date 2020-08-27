package br.com.gc.api.controller;

import br.com.gc.api.GlobalConstants;
import br.com.gc.api.model.*;
import br.com.gc.api.service.UserItemService;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@CrossOrigin
@RequestMapping("/api/v1/item")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserItemController {
    public final UserItemService userItemService;

    @PostMapping(path = "/newItemFromPanel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<VoPanelItem> newItemPanel(@RequestBody VoPanelItem item) throws Exception {
        log.info("Call service api/v1/newItemFromPanel " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(userItemService.addItemPanel(item));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/newItemFromPanel " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItem> newItem(@RequestBody UserItem item) throws Exception {
        log.info("Call service api/v1/add " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(userItemService.addItemUser(item));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/add " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @GetMapping(path = "/searchLastItem/item/{itemID}/login/{loginUID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItem> lastItem(@PathVariable Integer itemID, @PathVariable Integer loginUID) throws Exception {
        log.info("Call service api/v1/searchLastItem " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(userItemService.lastItemUser(itemID, loginUID));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/searchLastItem " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @PostMapping(path = "/add/card", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItemSocket> addCardItem(@RequestBody UserItemSocket card) throws Exception {
        log.info("Call service api/v1/add/card " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(userItemService.addCardItemUser(card));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/add/card " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @PostMapping(path = "/add/attribute", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItemAttribute> addAttributeItem(@RequestBody UserItemAttribute attribute) throws Exception
    {
        log.info("Call service api/v1/add/attribute " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(userItemService.addAttributeItemUser(attribute));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/add/attribute " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @PostMapping(path = "/strengthen", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserItemStrength> strengthenItem(@RequestBody UserItemStrength level) throws Exception {
        log.info("Call service api/v1/strengthen " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(userItemService.strengthenItemUser(level));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/strengthen " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }
}
