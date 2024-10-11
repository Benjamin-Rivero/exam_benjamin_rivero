package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.custom_response.CustomPage;
import fr.rivero.benjamin.dto.GameCreateDto;
import fr.rivero.benjamin.entity.Game;
import fr.rivero.benjamin.json_views.JsonViewGame;
import fr.rivero.benjamin.json_views.JsonViewUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import fr.rivero.benjamin.service.GameService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameController {


    private final GameService gameService;

    @PostMapping
    @JsonView(JsonViewGame.GameShow.class)
    public Game create(@RequestBody GameCreateDto gameCreateDto){
        return gameService.create(gameCreateDto);
    }

    @GetMapping
    @JsonView(JsonViewGame.GameList.class)
    public CustomPage<Game> list(@PageableDefault(
            size = 12,
            sort = {"createdAt"},
            direction = Sort.Direction.DESC) Pageable pageable, HttpServletRequest request){
        return gameService.list(pageable,request);
    }

    @GetMapping("/{id}")
    @JsonView(JsonViewGame.GameShow.class)
    public Game show(@PathVariable String id){
        return gameService.findById(id);
    }

    @GetMapping("/last")
    @JsonView(JsonViewGame.GameList.class)
    public List<Game> last() {
        return gameService.findTop10ByOrderByCreatedAtDesc();
    }

    @GetMapping("/scores")
    @JsonView(JsonViewGame.GameList.class)
    public List<Game> scores() {
        return gameService.findTop10Score();
    }

}