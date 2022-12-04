package com.dogukan.players.service.responses;

import com.dogukan.players.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllClubResponse {
    private int id;
    private String name;
    private String country;
    private List<GetAllPlayerResponse> players;
}
