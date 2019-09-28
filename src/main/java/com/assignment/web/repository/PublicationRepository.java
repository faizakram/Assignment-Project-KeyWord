package com.assignment.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.web.model.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
	

}
