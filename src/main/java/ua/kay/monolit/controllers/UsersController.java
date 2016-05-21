package ua.kay.monolit.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.Users;
import ua.kay.monolit.repositories.UsersRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public Users getUser(HttpServletRequest request,
                         HttpServletResponse response) throws UnsupportedEncodingException {

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

        Users users = usersRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(()-> new UserNotFoundException(username, password));
        return users;
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username, String password){
        super("Could not find user with username: "+username+" and password: "+password);
    }
}
