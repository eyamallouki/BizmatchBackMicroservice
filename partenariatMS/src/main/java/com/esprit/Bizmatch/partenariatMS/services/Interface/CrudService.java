package com.esprit.Bizmatch.partenariatMS.services.Interface;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.Entreprise;

import java.util.List;

public interface CrudService<T,ID>{
    List<T> retrieveAll();
    Entreprise add(T t);
    void update(T t);
    void remove(ID id);
    T retrieve(ID id);
}

