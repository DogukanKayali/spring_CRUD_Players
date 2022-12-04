package com.dogukan.players.controller;


import com.dogukan.players.service.PlayerService;
import com.dogukan.players.service.requests.CreatePlayerRequest;
import com.dogukan.players.service.requests.UpdatePlayerRequest;
import com.dogukan.players.service.responses.GetAllPlayerResponse;
import com.dogukan.players.Dto.PlayerDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getall")
    public List<GetAllPlayerResponse> findAll() {
        return playerService.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody CreatePlayerRequest playerRequest) {
        playerService.save(playerRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        playerService.delete(id);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody UpdatePlayerRequest playerRequest){
        playerService.update(playerRequest);
    }

    @GetMapping("/getbyid/{id}")
    public PlayerDto getById(@PathVariable int id){
       return playerService.getById(id);
    }

    @GetMapping("/getbyname/{name}")
    public List<PlayerDto> getByName(@PathVariable String name){
        return playerService.getByName(name);
    }
}
