package com.test.ntt.client.mappers;


import com.test.ntt.client.models.Client;
import com.test.ntt.client.models.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface MapperConfig {

    MapperConfig mapper = Mappers.getMapper(MapperConfig.class);

    ClientDto toClientDto(Client client);


}
