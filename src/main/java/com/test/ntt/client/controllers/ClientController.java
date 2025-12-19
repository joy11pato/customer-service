package com.test.ntt.client.controllers;


import com.test.ntt.client.messages.response.JsonResponse;
import com.test.ntt.client.models.Client;
import com.test.ntt.client.models.dto.ClientDto;
import com.test.ntt.client.models.dto.CreateClientDto;
import com.test.ntt.client.services.interfaces.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public Mono<ResponseEntity<JsonResponse<List<ClientDto>>>> getClients(){

        return clientService.listClients();

    }

    @PostMapping
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> create(@Valid @RequestBody CreateClientDto client){

        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> getClientById(@PathVariable Long id){

        return clientService.getClient(id);

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> update(@PathVariable Long id, @Valid @RequestBody CreateClientDto client){

        return clientService.update(id, client);

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<JsonResponse>> delete(@PathVariable Long id){

        return clientService.delete(id);

    }

    @GetMapping("/client/{id}")
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> getClientByIdCard(@PathVariable String idCard){

        return clientService.getClientByIdcard(idCard);

    }
}
