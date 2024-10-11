package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.dto.CoordinateCreateDto;
import fr.rivero.benjamin.entity.Coordinate;
import fr.rivero.benjamin.json_views.JsonViewCoordinate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.rivero.benjamin.service.CoordinateService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/coordinate")
public class CoordinateController {


    private final CoordinateService coordinateService;

    @PostMapping
    @JsonView(JsonViewCoordinate.CoordinateShow.class)
    public Coordinate create(@RequestBody CoordinateCreateDto coordinateCreateDto){
        return coordinateService.create(coordinateCreateDto);
    }

}