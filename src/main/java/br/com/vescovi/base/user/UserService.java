package br.com.vescovi.base.user;

import br.com.vescovi.base.framework.service.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BaseService<User, Long>, UserDetailsService {
}
