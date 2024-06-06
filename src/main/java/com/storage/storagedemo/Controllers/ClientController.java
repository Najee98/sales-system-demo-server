package com.storage.storagedemo.Controllers;

import com.storage.storagedemo.Dto.Requests.ClientRequestDto;
import com.storage.storagedemo.Dto.Responses.ClientResponseDto;
import com.storage.storagedemo.Models.Client;
import com.storage.storagedemo.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public ResponseEntity<List<ClientResponseDto>> getAllClients(){
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Client> getAllClients(@RequestBody ClientRequestDto request){
        return new ResponseEntity<>(clientService.createClient(request), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> getAllClients(
            @RequestParam Integer clientId,
            @RequestBody ClientRequestDto request
    ){
        return new ResponseEntity<>(clientService.updateClient(clientId, request), HttpStatus.OK);
    }
}
