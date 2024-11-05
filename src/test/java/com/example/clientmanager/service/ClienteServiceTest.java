package com.example.clientmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.clientmanager.model.Cliente;
import com.example.clientmanager.repository.ClienteRepository;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(new Cliente(), new Cliente()));

        // Act
        List<Cliente> clientes = clienteService.findAll();

        // Assert
        assertEquals(2, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        // Arrange
        Cliente cliente = new Cliente();
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Act
        clienteService.save(cliente);

        // Assert
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testFindById() {
        // Arrange
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Act
        Optional<Cliente> result = Optional.ofNullable(clienteService.findById(1L));

        // Assert
        assertTrue(result.isPresent());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testAtualizarCliente() {
        // Arrange
        Cliente existingCliente = new Cliente();
        existingCliente.setNome("Nome Antigo");
        
        Cliente updatedCliente = new Cliente();
        updatedCliente.setNome("Nome Atual");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(existingCliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(updatedCliente);

        // Act
        clienteService.atualizarCliente(1L, updatedCliente);

        // Assert
        assertEquals("Nome Atual", existingCliente.getNome());
        verify(clienteRepository, times(1)).save(existingCliente);
    }

    @Test
    public void testDelete() {
        // Act
        clienteService.delete(1L);

        // Assert
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
