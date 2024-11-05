package com.example.clientmanager.repository;

import com.example.clientmanager.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void testFindById() {
        Cliente cliente = new Cliente(null, "Teste Repositório", "repo@test.com", "12345-678", "Rua Repositório", "Bairro Repo", "Cidade Repo", "Estado Repo");
        cliente = clienteRepository.save(cliente);

        Optional<Cliente> resultado = clienteRepository.findById(cliente.getId());

        assertTrue(resultado.isPresent());
    }

}
