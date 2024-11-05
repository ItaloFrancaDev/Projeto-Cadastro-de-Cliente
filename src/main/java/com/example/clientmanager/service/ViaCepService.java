package com.example.clientmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.clientmanager.model.ViaCepResponse;

@Service
public class ViaCepService {

    private static final String VIACEP_API_URL = "https://viacep.com.br/ws/%s/json/";

    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(VIACEP_API_URL, cep);
        return restTemplate.getForObject(url, ViaCepResponse.class);
    }
}