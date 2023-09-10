package com.gnxcode.demoty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("v1")
public class CustomerController {
    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("index")
    public String index(){
        return "Hello world!";
    }

    @GetMapping("users")
    public String users(){
        return "User not security";
    }

    @GetMapping("session")
    public ResponseEntity<?> getDetailSession(){
        String sessionId = "";
        User userObject = null;

        List<Object> sessions = sessionRegistry.getAllPrincipals();

        for (Object session:sessions) {
            if(session instanceof User){
                userObject = (User) session;
            }

            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);

            for (SessionInformation sessionInformation : sessionInformations){
                sessionId = sessionInformation.getSessionId();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("response","Hello world");
        response.put("SessionId",sessionId);
        response.put("SessionUser",userObject);

        return ResponseEntity.ok(response);
    }
}
