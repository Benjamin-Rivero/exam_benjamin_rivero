package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.entity.User;
import fr.rivero.benjamin.json_views.JsonViewMap;
import fr.rivero.benjamin.json_views.JsonViewUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.rivero.benjamin.service.UserService;

import java.security.Principal;


@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;

    @GetMapping("/me")
    @JsonView(JsonViewUser.UserShowView.class)
    public User show(Principal principal){
        return userService.findByEmail(principal.getName());
    }

}