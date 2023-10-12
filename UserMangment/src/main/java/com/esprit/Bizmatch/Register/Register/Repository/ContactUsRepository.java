package com.esprit.Bizmatch.Register.Register.Repository;


import com.esprit.Bizmatch.Register.Register.entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
    ContactUs findByFirstNameAndLastName(String firstname, String lastname);

}