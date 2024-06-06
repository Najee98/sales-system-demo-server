package com.storage.storagedemo.Services;

import com.storage.storagedemo.Dto.Requests.ClientRequestDto;
import com.storage.storagedemo.Dto.Responses.ClientResponseDto;
import com.storage.storagedemo.Models.Client;

import java.util.List;

public interface ClientService {

    List<ClientResponseDto> getAllClients();

    Client getClientById(Integer clientId);

    Client createClient(ClientRequestDto request);

    Client updateClient(Integer clientId, ClientRequestDto request);
}
