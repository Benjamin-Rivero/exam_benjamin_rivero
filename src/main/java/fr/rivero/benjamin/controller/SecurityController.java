package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.custom_response.JwtResponse;
import fr.rivero.benjamin.dto.UserCreateDto;
import fr.rivero.benjamin.dto.LoginDto;
import fr.rivero.benjamin.entity.User;
import fr.rivero.benjamin.json_views.JsonViewMap;
import fr.rivero.benjamin.json_views.JsonViewUser;
import fr.rivero.benjamin.security.JwtAuthenticatorService;
import fr.rivero.benjamin.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SecurityController {

	private final UserService userService;
	private final JwtAuthenticatorService jwtAuthenticatorService;

	@PostMapping("/register")
	@JsonView(JsonViewUser.UserShowView.class)
	public User register(@Valid @RequestBody UserCreateDto userCreateDto){
		return userService.create(userCreateDto);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto){
		return jwtAuthenticatorService.authenticate(loginDto);
	}

}
