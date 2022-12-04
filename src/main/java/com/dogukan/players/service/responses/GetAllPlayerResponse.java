package com.dogukan.players.service.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPlayerResponse {
    private int id;
    private String first_name;
    private String last_name;
    private int club_id;
    private int age;
}
