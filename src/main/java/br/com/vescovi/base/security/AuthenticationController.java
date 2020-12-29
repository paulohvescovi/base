package br.com.vescovi.base.security;

import br.com.vescovi.base.security.jwt.JwtUtil;
import br.com.vescovi.base.security.payloads.JwtRequest;
import br.com.vescovi.base.security.payloads.JwtResponse;
import br.com.vescovi.base.user.User;
import br.com.vescovi.base.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthenticationController {

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtUtil jwtTokenUtil;
	@Autowired private UserService userService;

	//bean custom
	@Autowired @Qualifier("applicationNameBean") private String applicationNameBean;

	@Value("${spring.application.name}")
	private String applicationName;


	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
		);

		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder()
				.jwt(jwt)
				.username(userDetails.getUsername())
				.roles(Collections.singletonList(
						((User) authenticate.getPrincipal()).getRole()
				))
				.build();

		return ResponseEntity.ok(response);
	
	}
	
	

	@GetMapping(value = "/hello")
	public ResponseEntity<?> greetHello() throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		System.out.println(applicationNameBean);
		log.info("teste bem loco");
		return new ResponseEntity<>(applicationName, responseHeaders, HttpStatus.OK);
	}
}
