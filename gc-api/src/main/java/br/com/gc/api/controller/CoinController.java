package br.com.gc.api.controller;

import br.com.gc.api.constants.GlobalConstants;
import br.com.gc.api.model.CharacterInfo;
import br.com.gc.api.model.VirtualCash;
import br.com.gc.api.service.CoinService;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Slf4j
@CrossOrigin
@RequestMapping("/api/v1/coins")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoinController {
    public final CoinService coinService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MOD') and #oauth2.hasScope('read')")
    @GetMapping(path = "/getVP/loginUID/{loginUID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<VirtualCash> getVPUser(@PathVariable Integer loginUID) throws Exception {
        log.info("Call service api/v1/getVP " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(coinService.getVPInfo(loginUID).get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/getVP " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MOD') and #oauth2.hasScope('write')")
    @PostMapping(path = "/sendVP", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<VirtualCash> sendVPUser(@RequestBody VirtualCash vpUser) throws Exception {
        log.info("Call service api/v1/sendVP " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(coinService.sendVPUser(vpUser));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/sendVP " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MOD') and #oauth2.hasScope('write')")
    @PostMapping(path = "/sendGP", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CharacterInfo>> sendGPUser(@RequestBody CharacterInfo gpUser) throws Exception {
        log.info("Call service api/v1/sendGP " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        try {
            return ResponseEntity.ok().body(coinService.sendGPUser(gpUser));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            log.info("End service api/v1/sendGP " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }

}
