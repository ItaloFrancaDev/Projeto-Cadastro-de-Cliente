package com.example.clientmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clientmanager.model.Cliente;
import com.example.clientmanager.repository.ClienteRepository;

@Service 
public class ClienteService {
    @Autowired 
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll(); 
    }

    public void save(Cliente cliente) {
        clienteRepository.save(cliente); 
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = findById(id);
        if (clienteExistente != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setCep(clienteAtualizado.getCep());
            clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            clienteExistente.setBairro(clienteAtualizado.getBairro());
            clienteExistente.setCidade(clienteAtualizado.getCidade());
            clienteExistente.setEstado(clienteAtualizado.getEstado());
            clienteRepository.save(clienteExistente);
        }
    }
    
    public void delete(Long id) {
        clienteRepository.deleteById(id); 
    }

}
