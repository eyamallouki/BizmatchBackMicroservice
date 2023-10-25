package com.esprit.Bizmatch.partenariatMS.services.Implementation;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.DemandeAchat;
import com.esprit.Bizmatch.partenariatMS.repositories.DemandeAchatReporitory;
import com.esprit.Bizmatch.partenariatMS.services.Interface.DemandeAchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DemandeAchatServiceIMP  implements DemandeAchatService {
    @Autowired
    DemandeAchatReporitory demandeAchatReporitory;

    @Override
    public DemandeAchat addDemandeAchat(DemandeAchat demandeAchat) {
        return demandeAchatReporitory.save(demandeAchat);
    }

    @Override
    public void update(DemandeAchat demandeAchat) {
        demandeAchatReporitory.save(demandeAchat);
    }

    @Override
    public DemandeAchat findById(Integer id) {
            return demandeAchatReporitory.findById(id).orElse(null);

    }

    @Override
    public Optional<DemandeAchat> findById2(Integer id) {
        return demandeAchatReporitory.findById(id);
    }

    @Override
    public void deleteDemande(Integer id) {
        demandeAchatReporitory.deleteById(id);
    }

    @Override
    public List<DemandeAchat> findAll() {
        return demandeAchatReporitory.findAll();
    }


}
