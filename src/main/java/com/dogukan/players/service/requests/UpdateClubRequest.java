package com.dogukan.players.service.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClubRequest {
    private int id;
    private String name;
    private String country;

}
