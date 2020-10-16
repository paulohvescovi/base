package br.com.vescovi.base.configuration;

import br.com.vescovi.base.user.Role;
import br.com.vescovi.base.user.User;
import br.com.vescovi.base.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OnAfterApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired private UserService userService;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {

    //create user for test if not exists on start application
    if (Optional.ofNullable(userService.findById(1L)).isEmpty()){
      userService.save(
              User.builder()
                      .email("paulo20091994@gmail.com")
                      .password("$2a$10$tJr7tCcZGqxm5HNolzFXTuBUFQ5qP9/L0ZrU8t/hpS0DLoQg8e.QG")
                      .role(Role.ADMIN)
                      .build()
      );
    }

  }
}
