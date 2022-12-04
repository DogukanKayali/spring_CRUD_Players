package com.dogukan.players.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Oyuncunun ismini giriniz")
    private String first_name;
    @NotEmpty(message = "Oyuncunun Soyismini giriniz")
    private String last_name;
    @NotNull(message = "oyuncunun yaşını giriniz")
    private int age;
    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;
    @Override
    public String toString() {
        return
                "First Name ='" + first_name + ' ' +
                " Last Name='" + last_name;
    }
}
