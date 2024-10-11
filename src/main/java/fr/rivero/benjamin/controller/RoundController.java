package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.dto.RoundCreateDto;
import fr.rivero.benjamin.entity.Round;
import fr.rivero.benjamin.json_views.JsonViewMap;
import fr.rivero.benjamin.json_views.JsonViewRound;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import fr.rivero.benjamin.service.RoundService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/round")
public class RoundController {


    private final RoundService roundService;

    @PostMapping
    @JsonView(JsonViewRound.RoundShow.class)
    public Round create(@RequestBody RoundCreateDto roundCreateDto){
        return roundService.create(roundCreateDto);
    }

    @GetMapping("/{round}")
    public Integer calcPoint(@PathVariable("round") Long id){
        return roundService.calcPoints(id);
    }

}