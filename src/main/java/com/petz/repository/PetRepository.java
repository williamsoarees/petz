package com.petz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petz.entities.PetEntity;

public interface PetRepository extends CrudRepository<PetEntity, Integer>{

	@Query("SELECT p FROM PetEntity p WHERE p.nome LIKE %:nome%")
	List<PetEntity> buscarPetEntityPeloNome(@Param("nome") String nome);
	
}
