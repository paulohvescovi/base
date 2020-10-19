package br.com.vescovi.base.framework.controller;

import br.com.vescovi.base.framework.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public abstract class BaseController<T, ID>{

    protected abstract BaseService<T, ID> getService();

    @GetMapping
    private Page<T> find(@RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "20") Integer size){
        return getService().findAll(
                PageRequest.of(page, size)
        );
    }

    @GetMapping("{id}")
    private T findById(@PathVariable ID id){
        return getService().findById(id);
    }

    @PostMapping
    public T save(@Valid @RequestBody T entity) {
        return getService().save(entity);
    }

    @DeleteMapping("{id}")
    protected void delete(@PathVariable ID id){
        getService().delete(
                getService().findById(id)
        );
    }

}
