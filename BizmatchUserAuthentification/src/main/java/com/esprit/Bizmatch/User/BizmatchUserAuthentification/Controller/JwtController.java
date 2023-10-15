package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Controller;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service.JwtService;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.JwtRequest;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authen"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
