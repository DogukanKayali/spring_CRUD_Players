package com.dogukan.players.service;

import com.dogukan.players.model.Club;
import com.dogukan.players.model.Player;
import com.dogukan.players.repository.ClubRepository;
import com.dogukan.players.repository.PlayerRepository;
import com.dogukan.players.service.requests.CreateClubRequest;
import com.dogukan.players.Dto.ClubDto;
import com.dogukan.players.service.requests.UpdateClubRequest;
import com.dogukan.players.service.responses.GetAllClubResponse;
import com.dogukan.players.service.responses.GetAllPlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubManager implements ClubService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public ClubManager(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<GetAllClubResponse> findAll() {
        List<Club> clubs = clubRepository.findAll();
        List<Player> players = playerRepository.findAll();
        List<GetAllClubResponse> clubResponses = new ArrayList<>();

        for (Club club : clubs) {
            GetAllClubResponse clubResponse = new GetAllClubResponse();
            List<GetAllPlayerResponse> playerResponses = new ArrayList<>();

            clubResponse.setId(club.getId());
            clubResponse.setCountry(club.getCountry());
            clubResponse.setName(club.getName());
            clubResponse.setPlayers(playerResponses);
            clubResponses.add(clubResponse);

            for (Player player : players) {
                if (clubResponse.getId() == player.getClub().getId()) {
                    GetAllPlayerResponse playerResponse = new GetAllPlayerResponse();
                    playerResponse.setFirst_name(player.getFirst_name());
                    playerResponse.setLast_name(player.getLast_name());
                    playerResponse.setClub_id(player.getClub().getId());
                    playerResponse.setId(player.getId());
                    playerResponse.setAge(player.getAge());

                    playerResponses.add(playerResponse);
                }
            }
        }
        return clubResponses;
    }

    @Override
    public void save(CreateClubRequest clubRequest) {
        List<Club> clubs = clubRepository.findAll();
        ClubDto clubDto = new ClubDto();
        clubDto.setCountry(clubRequest.getCountry());
        clubDto.setName(clubRequest.getName());
        for (Club club:clubs){
            if (club.getName().equals(clubDto.getName())){
                throw new RuntimeException("----Bu Kulüp Mevcut---");
            }
        }
        Club club = new Club();
        club.setName(clubRequest.getName());
        club.setCountry(clubRequest.getCountry());

        clubRepository.save(club);

    }

    @Override
    public void delete(int id) {
        List<Club> clubs = clubRepository.findAll();
        for (Club club : clubs) {
            if (club.getId() == id)
                clubRepository.delete(club);
        }
    }

    @Override
    public ClubDto getById(int id) {
        ClubDto clubDto = new ClubDto();
        List<Club> clubs = clubRepository.findAll();
        List<Player> players = playerRepository.findAll();
        for (Club club : clubs) {
            if (club.getId() == id) {

                List<GetAllPlayerResponse> playerResponses = new ArrayList<>();
                for (Player player : players) {
                    if (player.getClub().getId() == club.getId()) {
                        GetAllPlayerResponse playerResponse = new GetAllPlayerResponse();
                        playerResponse.setAge(player.getAge());
                        playerResponse.setClub_id(player.getClub().getId());
                        playerResponse.setId(player.getId());
                        playerResponse.setLast_name(player.getLast_name());
                        playerResponse.setFirst_name(player.getFirst_name());
                        playerResponses.add(playerResponse);
                    }
                }
                clubDto.setPlayers(playerResponses);
                clubDto.setName(club.getName());
                clubDto.setCountry(club.getCountry());
                clubDto.setId(club.getId());
            }
        }
        return clubDto;
    }

    @Override
    public void update(UpdateClubRequest clubRequest) {
        Club club = new Club();
        club.setCountry(clubRequest.getCountry());
        club.setId(clubRequest.getId());
        club.setName(clubRequest.getName());
        List<Player> players = playerRepository.findAll();
        List<Player> playerList = new ArrayList<>();
        for (Player player:players){
            if (player.getClub().getId() == clubRequest.getId()){
                playerList.add(player);
            }
        }
        club.setPlayers(playerList);
        clubRepository.save(club);
    }

    @Override
    public List<ClubDto> getByName(String name) {
        List<Club> clubs = clubRepository.findAll();
        List<ClubDto> clubDtos = new ArrayList<>();
        ClubDto clubDto = new ClubDto();
        for (Club club:clubs){
            if (club.getName().equalsIgnoreCase(name)){

                clubDto.setName(club.getName());
                clubDto.setId(club.getId());
                clubDto.setCountry(club.getCountry());
                List<Player> players = club.getPlayers();
                List<GetAllPlayerResponse> playerResponses = new ArrayList<>();
                for (Player player:players){

                    GetAllPlayerResponse playerResponse = new GetAllPlayerResponse();
                    playerResponse.setId(player.getId());
                    playerResponse.setClub_id(player.getClub().getId());
                    playerResponse.setAge(player.getAge());
                    playerResponse.setLast_name(player.getLast_name());
                    playerResponse.setFirst_name(player.getFirst_name());
                    playerResponses.add(playerResponse);
                }
                clubDto.setPlayers(playerResponses);
            }
        }if (clubDto.getId() != 0){
            clubDtos.add(clubDto);
        }

        if (clubDtos.isEmpty()) {
            throw new RuntimeException("Kulüp bulunamadı");
        }
        else {
            return clubDtos;
        }


    }
}
