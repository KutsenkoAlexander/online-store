package ua.kay.monolit.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.exceptions.UserNotFoundException;
import ua.kay.monolit.repositories.UsersRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public void getUser(HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        byte[] valueDecoded= Base64.decodeBase64(request.getHeader("authorization"));
        String loginAndPass = new String(valueDecoded, "UTF-8");

        int separatePosition = loginAndPass.indexOf(":");
        String username = loginAndPass.substring(0, separatePosition);
        String password = loginAndPass.substring(separatePosition+1);

        usersRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(()-> new UserNotFoundException(username, password));

    }
}
