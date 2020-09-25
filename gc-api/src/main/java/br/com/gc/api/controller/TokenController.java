package br.com.gc.api.controller;

import br.com.gc.api.constants.GlobalConstants;
import br.com.gc.api.util.DateFormatSQLServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/token")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Authentication endpoints")
public class TokenController {

    @DeleteMapping("/revoke")
    @ApiOperation(value = "User logout")
    public void revoke(HttpServletRequest req, HttpServletResponse resp) {
        try {
            log.info("Call service api/v1/token/revoke " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
            log.info("Revoking token ");
            Cookie cookie = new Cookie("refreshToken", null);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);// TODO: alterar em producao
            cookie.setPath(req.getContextPath() + "/oauth/token");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            resp.setStatus(HttpStatus.NO_CONTENT.value());
        } finally {
            log.info("End service api/v1/token/revoke " + DateFormatSQLServer.format(new Date(), GlobalConstants.CALL_SERVICE_FORMAT));
        }
    }
}
