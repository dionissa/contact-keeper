package org.vaadin.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vaadin.example.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(:searchTerm) " +
            "OR LOWER(c.phoneNumber) LIKE LOWER(:searchTerm) " +
            "OR LOWER(c.notes) LIKE LOWER(:searchTerm)")
    List<Client> findBySearchTerm(@Param("searchTerm") String searchTerm);

    List<Client> findByNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCaseOrNotesContainingIgnoreCase(String name,
            String phoneNumber, String notes);

}
