package br.com.vescovi.base.security.payloads;

import br.com.vescovi.base.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse implements Serializable {
	
    private String jwt;
	private String username;
    private List<Role> roles;

}
