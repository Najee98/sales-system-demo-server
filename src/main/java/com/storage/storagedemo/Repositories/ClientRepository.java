package com.storage.storagedemo.Repositories;

import com.storage.storagedemo.Dto.Responses.ClientResponseDto;
import com.storage.storagedemo.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select new com.storage.storagedemo.Dto.Responses.ClientResponseDto(" +
            "c.id," +
            "c.firstName," +
            "c.lastName," +
            "c.mobile) " +
            "from Client c")
    List<ClientResponseDto> findAllClients();
}
