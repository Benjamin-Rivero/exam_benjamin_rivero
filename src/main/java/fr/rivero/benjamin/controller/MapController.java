package fr.rivero.benjamin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.custom_response.CustomPage;
import fr.rivero.benjamin.dto.MapCreateDto;
import fr.rivero.benjamin.entity.Map;
import fr.rivero.benjamin.json_views.JsonViewGame;
import fr.rivero.benjamin.json_views.JsonViewMap;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import fr.rivero.benjamin.service.MapService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/map")
public class MapController {


    private final MapService mapService;

    @GetMapping
    @JsonView(JsonViewMap.MapListView.class)
    public CustomPage<Map> list(@PageableDefault(
            size = 12,
            sort = {"createdAt"},
            direction = Sort.Direction.DESC) Pageable pageable, HttpServletRequest request){
        return mapService.list(pageable,request);
    }

    @GetMapping("/{id}")
    @JsonView(JsonViewMap.MapShowView.class)
    public Map show(@PathVariable Long id){
        return mapService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViewMap.MapShowView.class)
    public Map create(@RequestBody MapCreateDto mapCreateDto){
        return mapService.create(mapCreateDto);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViewMap.MapShowView.class)
    public Map update(@RequestBody MapCreateDto mapCreateDto,@PathVariable Long id){
        return mapService.update(mapCreateDto,id);
    }

    @GetMapping("/best")
    @JsonView(JsonViewMap.MapListView.class)
    public List<Map> best(){
        return mapService.findTop5MapPlayed();
    }

}