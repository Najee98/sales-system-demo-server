package com.storage.storagedemo.Services;

import com.storage.storagedemo.Dto.Requests.ClientRequestDto;
import com.storage.storagedemo.Dto.Responses.ClientResponseDto;
import com.storage.storagedemo.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.storage.storagedemo.Models.Client;
import com.storage.storagedemo.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;


    @Override
    public List<ClientResponseDto> getAllClients() {
        List<ClientResponseDto> clients = clientRepository.findAllClients();
        return clients;
    }

    @Override
    public Client getClientById(Integer clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Client with id: " + clientId + " not found")
                );
    }

    @Override
    public Client createClient(ClientRequestDto request) {
        Client client = new Client();

        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setMobile(request.getMobile());

        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Integer clientId, ClientRequestDto request) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Client with id: " + clientId + " not found")
                );

        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setMobile(request.getMobile());

        return clientRepository.save(client);
    }
}
