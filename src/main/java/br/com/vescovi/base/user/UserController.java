package br.com.vescovi.base.user;

import br.com.vescovi.base.framework.controller.BaseController;
import br.com.vescovi.base.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Long> {

    @Autowired private UserService userService;

    @Override
    protected BaseService<User, Long> getService() {
        return userService;
    }
}
