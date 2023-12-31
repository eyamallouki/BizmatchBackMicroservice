package com.esprit.Bizmatch.partenariatMS.services.Implementation;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.Entreprise;
import com.esprit.Bizmatch.partenariatMS.repositories.CrudRepository;
import com.esprit.Bizmatch.partenariatMS.services.Interface.CrudService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CrudServiceIMP<T,ID> implements CrudService<T,ID> {

    @Autowired
    public CrudRepository<T,ID> crudRepository;

    @Override
    public List<T> retrieveAll() {
        try{
            return  crudRepository.findAll();
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }

    @Override
    public Entreprise add(T t) {
        try{
            crudRepository.save(t);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }

        return null;
    }

    @Override
    public void update(T t) {
        try {
            System.out.println("mise a jour avec succes");
            crudRepository.save(t);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }

    }

    @Override
    public void remove(ID id) {
        try{
            T t = crudRepository.findById(id).orElse(null);
            crudRepository.delete(t);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
    }

    @Override
    public T retrieve(ID id) {
        try{
            return  crudRepository.findById(id).get();
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }

}



