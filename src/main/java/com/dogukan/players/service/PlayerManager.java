package com.dogukan.players.service;

import com.dogukan.players.model.Club;
import com.dogukan.players.model.Player;
import com.dogukan.players.repository.ClubRepository;
import com.dogukan.players.repository.PlayerRepository;
import com.dogukan.players.service.requests.CreatePlayerRequest;
import com.dogukan.players.service.requests.UpdatePlayerRequest;
import com.dogukan.players.service.responses.GetAllPlayerResponse;
import com.dogukan.players.Dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class PlayerManager implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public PlayerManager(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<GetAllPlayerResponse> findAll() {
        List<Player> players = playerRepository.findAll();
        List<GetAllPlayerResponse> responses = new ArrayList<>();

        //responses = players.stream().forEach;

        for (Player player : players) {
            GetAllPlayerResponse response = new GetAllPlayerResponse();
            response.setId(player.getId());
            response.setFirst_name(player.getFirst_name());
            response.setLast_name(player.getLast_name());
            response.setAge(player.getAge());
            response.setClub_id(player.getClub().getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public void save(CreatePlayerRequest playerRequest) {
        Player player = new Player();
        List<Club> clubs = clubRepository.findAll();
        List<Player> players = playerRepository.findAll();
        for (Club club : clubs) {
            if (club.getId() == playerRequest.getClub_id()) {
                player.setClub(club);
                player.setAge(playerRequest.getAge());
                player.setFirst_name(playerRequest.getFirst_name());
                player.setLast_name(playerRequest.getLast_name());
                for (Player player1 : players) {
                    if (player1.getClub() == player.getClub()
                            && player1.getFirst_name().equals(player.getFirst_name())
                            && player1.getLast_name().equals(player.getLast_name())) {
                        throw new RuntimeException("Bu oyuncu mevcut");
                    } else {
                        playerRepository.save(player);
                    }
                }

            }
        }
    }

    @Override
    public void delete(int id) {
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            if (player.getId() == id) {
                playerRepository.delete(player);
            }
        }
    }

    @Override
    public void update(UpdatePlayerRequest playerRequest) {
        Player player = new Player();
        List<Club> clubs = clubRepository.findAll();
        for (Club club : clubs) {
            if (club.getId() == playerRequest.getClub_id()) {
                player.setClub(club);
                player.setId(playerRequest.getId());
                player.setAge(playerRequest.getAge());
                player.setFirst_name(playerRequest.getFirst_name());
                player.setLast_name(playerRequest.getLast_name());
                playerRepository.save(player);
            }
        }
    }

    @Override
    public PlayerDto getById(int id) {
        List<Player> players = playerRepository.findAll();
        PlayerDto playerDto = new PlayerDto();
        for (Player player : players) {
            if (player.getId() == id) {

                playerDto.setAge(player.getAge());
                playerDto.setFirst_name(player.getFirst_name());
                playerDto.setId(player.getId());
                playerDto.setLast_name(player.getLast_name());
                playerDto.setClub_id(player.getClub().getId());

            }
        }
        return playerDto;
    }

    @Override
    public List<PlayerDto> getByName(String name) {
        List<Player> players = playerRepository.findAll();
        List<PlayerDto> playerDtos = new ArrayList<>();

        for (Player player : players) {
            if (player.getFirst_name().equalsIgnoreCase(name)) {
                PlayerDto playerDto = new PlayerDto();
                playerDto.setClub_id(player.getClub().getId());
                playerDto.setAge(player.getAge());
                playerDto.setFirst_name(player.getFirst_name());
                playerDto.setLast_name(player.getLast_name());
                playerDto.setId(player.getId());
                playerDtos.add(playerDto);

            }

        }
        if (playerDtos.isEmpty()) {
            throw new RuntimeException("Bu isimde oyuncu yok");
        } else {
            return playerDtos;
        }
    }
}
