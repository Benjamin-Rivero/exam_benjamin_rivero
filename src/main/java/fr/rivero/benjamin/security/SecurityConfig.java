package fr.rivero.benjamin.security;

import fr.rivero.benjamin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserService userService;
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
				.securityMatcher("/api/**")
				.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth ->
						auth
								.requestMatchers( "/api/login", "/api/register").permitAll()
								.requestMatchers(
										antMatcher("/v3/api-docs/**"),
										antMatcher("/swagger-ui/**"),
										antMatcher("/api/game/last"),
										antMatcher("/api/game/scores"),
										antMatcher("/api/map/best"),
										antMatcher("/api/round/**")
								).permitAll()
								.requestMatchers(
										antMatcher( "/api/user/**"),
										antMatcher(HttpMethod.POST,"/api/coordinate"),
										antMatcher(HttpMethod.POST,"/api/round"),
										antMatcher("/api/game/**"),
										antMatcher("/api/game"),
										antMatcher("/api/map/**"),
										antMatcher("/api/map")
								).authenticated()
								.requestMatchers(
										antMatcher(HttpMethod.POST,"/api/admin/**")
								).hasAnyAuthority("ROLE_ADMIN")
								.requestMatchers(antMatcher("/api/**"))
								.hasAnyAuthority("ROLE_ADMIN")
				);
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userService);
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
