package com.example.platformerp.service.base;


import com.example.platformerp.exceptions.DataNotFoundException;
import com.example.platformerp.model.BaseEntity;
import com.example.platformerp.service.validator.AbstractValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseService<
            E extends BaseEntity,
            ID,REP extends JpaRepository<E,ID>,
            REQ,RES,V extends AbstractValidator<E,REP>
        > {

    protected final REP repository;
    protected final V validator;
    protected final ModelMapper modelMapper;

    public RES create (REQ request) {
        E entity = mapCRToEntity(request);
        validator.validate(entity);
        repository.save(entity);
        return mapEntityToRes(entity);
    }

    public RES findById(ID id) {
        E e = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("resource with id:" + id + " not found")
                );
        return mapEntityToRes(e);
    }
    public List<RES> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        Page<E> all = repository.findAll(pageable);
        return all.get().map(this::mapEntityToRes).toList();
    }
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    protected abstract RES mapEntityToRes(E entity);

    protected abstract E mapCRToEntity(REQ req);
}
