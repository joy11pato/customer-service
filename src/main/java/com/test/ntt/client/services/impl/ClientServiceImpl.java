package com.test.ntt.client.services.impl;

import com.test.ntt.client.mappers.MapperConfig;
import com.test.ntt.client.messages.response.JsonResponse;
import com.test.ntt.client.models.Client;
import com.test.ntt.client.models.dto.ClientDto;
import com.test.ntt.client.models.dto.CreateClientDto;
import com.test.ntt.client.repositories.ClientRepository;
import com.test.ntt.client.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Mono<ResponseEntity<JsonResponse<List<ClientDto>>>> listClients() {

        // 1. Envolvemos la operación bloqueante en fromCallable
        return Mono.fromCallable(() -> {
                    // Esta línea bloquea, por eso va dentro del Callable
                    return clientRepository.findAll();
                })
                // 2. ¡IMPORTANTE! Movemos la ejecución a un hilo dedicado para I/O bloqueante
                .subscribeOn(Schedulers.boundedElastic())

                // 3. Transformamos la List<Client> a List<ClientDto>
                .map(entityList -> {
                    return entityList.stream()
                            .map(client -> {
                                // Aquí conviertes cada entidad a DTO
                                ClientDto dto = MapperConfig.mapper.toClientDto(client);
                                // ... setear resto de campos
                                return dto;
                            })
                            .collect(Collectors.toList());
                })

                // 4. Construimos la respuesta final
                .map(dtoList -> {
                    JsonResponse<List<ClientDto>> response = new JsonResponse<>(true, "success", dtoList);
                    return ResponseEntity.ok(response);
                });
    }

    @Override
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> getClient(Long id) {

        return Mono.fromCallable(() -> {
                    // Esta línea bloquea, por eso va dentro del Callable
                    return clientRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(savedClient -> {
                    ClientDto clientDto = MapperConfig.mapper.toClientDto(savedClient);
                    log.info("ClientDto condultado: {}", clientDto);
                    JsonResponse<ClientDto> resp = new JsonResponse<>(true, "success", clientDto);
                    return ResponseEntity.ok(resp);
                });
    }

    @Override
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> createClient(CreateClientDto dto) {
        return Mono.fromCallable(() -> {
                    // Lógica bloqueante aquí dentro
                    Client client = new Client(
                            dto.getName(),
                            dto.getAddress(),
                            dto.getPhone(),
                            dto.getIdCard(),
                            dto.getGender(),
                            UUID.randomUUID().toString());
                    return clientRepository.save(client);
                })
                .subscribeOn(Schedulers.boundedElastic()) // ¡Vital! Mueve el bloqueo a otro hilo
                .map(savedClient -> {
                    ClientDto clientDto = MapperConfig.mapper.toClientDto(savedClient);
                    JsonResponse<ClientDto> resp = new JsonResponse<>(true, "success", clientDto);
                    return ResponseEntity.ok(resp);
                });
    }

    @Override
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> update(Long id, CreateClientDto dto) {

        return Mono.fromCallable(() -> {
                    // Esta línea bloquea, por eso va dentro del Callable
                    return clientRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Client not found"));
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(c -> {
                    c.setName(dto.getName());
                    c.setGender(dto.getGender());
                    c.setPhone(dto.getPhone());
                    c.setIdCard(dto.getIdCard());
                    c.setAddress(dto.getAddress());

                    clientRepository.save(c);

                    ClientDto clientDto = MapperConfig.mapper.toClientDto(c);

                    JsonResponse<ClientDto> resp = new JsonResponse<>(true, "success", clientDto);
                    return ResponseEntity.ok(resp);
                });
    }

    @Override
    public Mono<ResponseEntity<JsonResponse>> delete(Long id) {
        return Mono.fromCallable(() -> {

                    return clientRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Client not found"));
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(c -> {

                    clientRepository.delete(c);

                    JsonResponse<ClientDto> resp = new JsonResponse<>(true, "success", null);
                    return ResponseEntity.ok(resp);
                });
    }

    @Override
    public Mono<ResponseEntity<JsonResponse<ClientDto>>> getClientByIdcard(String idcard) {
        return Mono.fromCallable(() -> {
                    // Esta línea bloquea, por eso va dentro del Callable
                    return clientRepository.findByIdCard(idcard)
                            .orElseThrow(() -> new RuntimeException("Client not Found"));
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(savedClient -> {
                    ClientDto clientDto = MapperConfig.mapper.toClientDto(savedClient);
                    JsonResponse<ClientDto> resp = new JsonResponse<>(true, "success", clientDto);
                    return ResponseEntity.ok(resp);
                });
    }
}
