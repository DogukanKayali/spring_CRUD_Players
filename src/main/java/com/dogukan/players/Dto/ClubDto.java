package com.dogukan.players.Dto;

import com.dogukan.players.service.responses.GetAllPlayerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubDto {
    private int id;
    private String name;
    private String country;
    private List<GetAllPlayerResponse> players;
}
