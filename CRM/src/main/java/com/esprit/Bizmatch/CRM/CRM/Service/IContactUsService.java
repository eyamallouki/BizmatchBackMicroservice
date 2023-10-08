package com.esprit.Bizmatch.CRM.CRM.Service;

import com.esprit.Bizmatch.CRM.CRM.Entity.AnalyseSentiment;
import com.esprit.Bizmatch.CRM.CRM.Entity.ContactUs;
import com.esprit.Bizmatch.CRM.CRM.Entity.PourcentageSentiment;

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


