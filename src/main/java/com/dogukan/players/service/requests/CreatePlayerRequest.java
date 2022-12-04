package com.dogukan.players.service.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequest {
    @NotEmpty(message = "Oyuncunun ismini giriniz")
    private String first_name;
    @NotEmpty(message = "Oyuncunun Soyismini giriniz")
    private String last_name;
    @NotNull(message = "oyuncunun yaşını giriniz")
    private int age;
    private int club_id;
}
