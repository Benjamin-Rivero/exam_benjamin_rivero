package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.custom_response.CustomResponse;
import fr.rivero.benjamin.entity.User;
import fr.rivero.benjamin.json_views.JsonViewMap;
import fr.rivero.benjamin.json_views.JsonViewUser;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import fr.rivero.benjamin.service.UserService;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/uploadAvatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CustomResponse<String> uploadAvatar(@RequestBody MultipartFile file, Principal principal){
        return userService.uploadAvatar(file,principal);
    }

}