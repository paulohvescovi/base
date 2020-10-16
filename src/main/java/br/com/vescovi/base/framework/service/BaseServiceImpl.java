package br.com.vescovi.base.framework.service;

import br.com.vescovi.base.framework.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    protected abstract JpaRepository<T, ID> getData();

    @Override
    public List<T> findAll() {
        return getData().findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getData().findAll(pageable);
    }

    @Override
    public void delete(T bean) {
        getData().delete(bean);
    }

    @Override
    public T save(T bean) {
        preProcessorSave(bean);
        getData().save(bean);
        postProcessorSave(bean);
        return bean;
    }

    @Override
    public T findById(ID id) {
        return getData().findById(id).orElse(null);
    }

    @Override
    public T postProcessorSave(T entity) {
        return entity;
    }

    @Override
    public T preProcessorSave(T entity) {
        return entity;
    }

}
