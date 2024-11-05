package com.example.clientmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.clientmanager.model.Cliente;
import com.example.clientmanager.model.ViaCepResponse;
import com.example.clientmanager.repository.ClienteRepository;
import com.example.clientmanager.service.ClienteService;
import com.example.clientmanager.service.ViaCepService;


@Controller 
public class ClienteController {
    
	@Autowired 
    private ClienteService clienteService;

	 @Autowired
	    private ClienteRepository clienteRepository;

	    @Autowired
	    private ViaCepService viaCepService;
	    
    @GetMapping("/") 
    public String index(Model model) {
        List<Cliente> clientes = clienteService.findAll(); 
        model.addAttribute("clientes", clientes); 
        return "index"; 
    }

    @GetMapping("/buscar")
    public String buscarCliente(@RequestParam String search, Model model) {
        List<Cliente> clientes;
        if (search.matches("\\d+")) { 
            Long id = Long.valueOf(search);
            clientes = List.of(clienteRepository.findById(id).orElse(null)); 
        } else { 
            clientes = clienteRepository.findByNomeContainingIgnoreCase(search); 
        }
        model.addAttribute("clientes", clientes);
        return "index"; 
    }
    
    @GetMapping("/cadastro")
    public String showForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String saveCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            redirectAttributes.addFlashAttribute("emailError", "Este e-mail já está cadastrado!");
            return "redirect:/cadastro"; 
        }
        
        ViaCepResponse endereco = viaCepService.buscarEnderecoPorCep(cliente.getCep());
        if (endereco != null) {
            cliente.setEndereco(endereco.getLogradouro());
            cliente.setBairro(endereco.getBairro());
            cliente.setCidade(endereco.getLocalidade());
            cliente.setEstado(endereco.getUf());
        }
        
        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute("message", "Cliente cadastrado com sucesso!");
        return "redirect:/";
    }

    
    @GetMapping("/editar")
    public String editarCliente(@RequestParam Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return "redirect:/"; 
        }
        model.addAttribute("cliente", cliente);
        return "editarCliente"; 
    }

   
    @PostMapping("/editar/{id}")
    public String salvarEdicaoCliente(@PathVariable Long id, @ModelAttribute Cliente clienteAtualizado, RedirectAttributes redirectAttributes) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setCep(clienteAtualizado.getCep());
            clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            clienteExistente.setBairro(clienteAtualizado.getBairro());
            clienteExistente.setCidade(clienteAtualizado.getCidade());
            clienteExistente.setEstado(clienteAtualizado.getEstado());

            clienteRepository.save(clienteExistente); 
            redirectAttributes.addFlashAttribute("message", "Cliente atualizado com sucesso!");
        }
        return "redirect:/"; 
    }

    @GetMapping("/deletar") 
    public String deletar(Long id) {
        clienteService.delete(id); 
        return "redirect:/"; 
    }

}
