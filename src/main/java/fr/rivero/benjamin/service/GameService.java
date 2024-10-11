package fr.rivero.benjamin.service;

import fr.rivero.benjamin.custom_response.CustomPage;
import fr.rivero.benjamin.dto.GameCreateDto;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import fr.rivero.benjamin.entity.Game;
import fr.rivero.benjamin.repository.GameRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final MapService mapService;

    public Game create(GameCreateDto gameCreateDto){
        return gameRepository.saveAndFlush(toEntity(gameCreateDto));
    }

    private Game toEntity(GameCreateDto gameCreateDto) {
        Game game = new Game();
        game.setCreatedAt(LocalDateTime.now());
        game.setHasMove(gameCreateDto.getHasMove());
        game.setHasPan(gameCreateDto.getHasPan());
        game.setHasZoom(gameCreateDto.getHasZoom());
        game.setNbRounds(game.getNbRounds());
        game.setMap(mapService.findById(gameCreateDto.getMapId()));
        return game;
    }

    public Game findById(String id) {
        return gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public CustomPage<Game> list(Pageable pageable, HttpServletRequest request) {
        Page<Game> page = gameRepository.findAll(pageable);
        return new CustomPage<>(HttpStatus.OK,page,request);
    }

    public List<Game> findTop10ByOrderByCreatedAtDesc() {
        return gameRepository.findTop10ByOrderByCreatedAtDesc();
    }

    public List<Game> findTop10Score() {
        return gameRepository.findTop10Score();
    }
}