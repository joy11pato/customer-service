package com.test.ntt.client.services.interfaces;

import com.test.ntt.client.messages.response.JsonResponse;
import com.test.ntt.client.models.Client;
import com.test.ntt.client.models.dto.ClientDto;
import com.test.ntt.client.models.dto.CreateClientDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientService {

    Mono<ResponseEntity<JsonResponse<List<ClientDto>>>> listClients();

    Mono<ResponseEntity<JsonResponse<ClientDto>>> getClient(Long id);

    Mono<ResponseEntity<JsonResponse<ClientDto>>> createClient(CreateClientDto dto);

    Mono<ResponseEntity<JsonResponse<ClientDto>>> update(Long id, CreateClientDto dto);

    Mono<ResponseEntity<JsonResponse>> delete(Long id);

    Mono<ResponseEntity<JsonResponse<ClientDto>>> getClientByIdcard(String idcard);

}
