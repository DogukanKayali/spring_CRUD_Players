package com.dogukan.players.repository;

import com.dogukan.players.Dto.ClubDto;
import com.dogukan.players.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {

}
