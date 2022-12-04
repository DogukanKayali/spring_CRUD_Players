package com.dogukan.players.service;

import com.dogukan.players.service.requests.CreatePlayerRequest;
import com.dogukan.players.service.requests.UpdatePlayerRequest;
import com.dogukan.players.service.responses.GetAllPlayerResponse;
import com.dogukan.players.Dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<GetAllPlayerResponse> findAll();

    void save(CreatePlayerRequest playerRequest);

    void delete(int id);

    void update(UpdatePlayerRequest playerRequest);

    PlayerDto getById(int id);

    List<PlayerDto> getByName(String name);
}
