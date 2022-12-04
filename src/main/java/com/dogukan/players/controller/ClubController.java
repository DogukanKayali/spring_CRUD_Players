package com.dogukan.players.controller;

import com.dogukan.players.service.ClubService;
import com.dogukan.players.service.requests.CreateClubRequest;
import com.dogukan.players.Dto.ClubDto;
import com.dogukan.players.service.requests.UpdateClubRequest;
import com.dogukan.players.service.responses.GetAllClubResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/getall")
    public List<GetAllClubResponse> findAll() {
        return clubService.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@Valid @RequestBody CreateClubRequest clubRequest) {
        clubService.save(clubRequest);
        return clubRequest.toString() + " Listeye eklendi";
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        clubService.delete(id);
    }
    @GetMapping("/getbyid/{id}")
    public ClubDto getById(@PathVariable int id){
        return clubService.getById(id);
    }
    @PutMapping("/update")
    public void update(@RequestBody UpdateClubRequest clubRequest){
        clubService.update(clubRequest);
    }
    @GetMapping("/getbyname/{name}")
    public List<ClubDto> getByName(@PathVariable String name){
        return clubService.getByName(name);
    }
}
