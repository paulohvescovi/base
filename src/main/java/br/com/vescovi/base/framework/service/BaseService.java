package br.com.vescovi.base.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T, ID> {

    T findById(ID id);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    T save(T entity);

    void delete(T entity);

    T preProcessorSave(T entity);

    T postProcessorSave(T entity);

}
