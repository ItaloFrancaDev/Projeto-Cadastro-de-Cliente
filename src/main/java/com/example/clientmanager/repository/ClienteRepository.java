package com.example.clientmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clientmanager.model.Cliente;

public interface  ClienteRepository extends JpaRepository<Cliente, Long> {
	boolean existsByEmail(String email);
	 List<Cliente> findByNomeContainingIgnoreCase(String nome);
	    
}
