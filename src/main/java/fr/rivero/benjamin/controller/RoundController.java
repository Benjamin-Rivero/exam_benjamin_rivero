package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.dto.RoundCreateDto;
import fr.rivero.benjamin.entity.Round;
import fr.rivero.benjamin.json_views.JsonViewMap;
import fr.rivero.benjamin.json_views.JsonViewRound;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}