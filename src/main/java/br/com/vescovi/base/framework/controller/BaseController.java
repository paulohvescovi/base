package br.com.vescovi.base.framework.controller;

import br.com.vescovi.base.framework.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class BaseController<T, ID>{

    protected abstract BaseService<T, ID> getService();

    @GetMapping
    private List<T> findAll(){
        return getService().findAll();
    }

    @GetMapping
    private Page<T> findAll(Pageable pageable){
        return getService().findAll(pageable);
    }

    @GetMapping("{id}")
    private T findById(@PathVariable ID id){
        return getService().findById(id);
    }

    @PostMapping
    public T save(@RequestBody T entity) {
        return getService().save(entity);
    }

    @DeleteMapping("{id}")
    protected void delete(@PathVariable ID id){
        getService().delete(
                getService().findById(id)
        );
    }

}
