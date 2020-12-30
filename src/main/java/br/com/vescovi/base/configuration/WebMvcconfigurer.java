package br.com.vescovi.base.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcconfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        //paginao padrao de 5 em 5
        PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
        pageHandler.setFallbackPageable(PageRequest.of(0,5));
        resolvers.add(pageHandler);


    }
}
