package com.dogukan.players.service;

import com.dogukan.players.service.requests.CreateClubRequest;
import com.dogukan.players.Dto.ClubDto;
import com.dogukan.players.service.requests.UpdateClubRequest;
import com.dogukan.players.service.responses.GetAllClubResponse;

import java.util.List;

public interface ClubService {
    List<GetAllClubResponse> findAll();

    void save(CreateClubRequest clubRequest);

    void delete(int id);

    ClubDto getById(int id);

    void update(UpdateClubRequest clubRequest);

    List<ClubDto> getByName(String name);
}
