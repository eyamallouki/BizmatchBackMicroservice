package com.esprit.Bizmatch.Register.Register.Controller;


import com.esprit.Bizmatch.Register.Register.Service.JwtService;
import com.esprit.Bizmatch.Register.Register.entity.JwtRequest;
import com.esprit.Bizmatch.Register.Register.entity.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtController {

    @Autowired
    private JwtService jwtService;

   /* @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }*/
}
