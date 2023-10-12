package com.esprit.Bizmatch.Register.Register.Service;



import com.esprit.Bizmatch.Register.Register.entity.AnalyseSentiment;
import com.esprit.Bizmatch.Register.Register.entity.ContactUs;
import com.esprit.Bizmatch.Register.Register.entity.PourcentageSentiment;

import java.util.List;


public interface IContactUsService{
        ContactUs createContactUs(ContactUs contactUs);
        List<ContactUs> getAllContactUs();
        ContactUs getContactUsById(Long id);
        ContactUs updateContactUs(Long id, ContactUs updatedContactUs);
        boolean deleteContactUs(Long id);

        public ContactUs getContactUsByFirstnameAndLastname(String firstname, String lastname);

        public AnalyseSentiment analyserContenu(ContactUs contactUs);


        public PourcentageSentiment calculerPourcentageSentiment1(ContactUs contactUs);

        public AnalyseSentiment analyserContenuParId(long idContact);

        public PourcentageSentiment calculerPourcentageSentimentGlobal();
}


