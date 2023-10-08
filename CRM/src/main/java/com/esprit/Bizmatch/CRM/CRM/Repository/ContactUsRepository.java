package com.esprit.Bizmatch.CRM.CRM.Repository;

import com.esprit.Bizmatch.CRM.CRM.Entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
    ContactUs findByFirstNameAndLastName(String firstname, String lastname);

}