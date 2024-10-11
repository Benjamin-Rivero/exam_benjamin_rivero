package fr.rivero.benjamin.service;

import fr.rivero.benjamin.custom_response.CustomPage;
import fr.rivero.benjamin.dto.MapCreateDto;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import fr.rivero.benjamin.entity.Map;
import fr.rivero.benjamin.repository.MapRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    public Map findById(Long id) {
        return mapRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public CustomPage<Map> list(Pageable pageable, HttpServletRequest request) {
        Page<Map> page = mapRepository.findAll(pageable);
        return new CustomPage<>(HttpStatus.OK,page,request);
    }

    public Map create(MapCreateDto mapCreateDto) {
        Map map = toEntity(mapCreateDto);
        map.setCreatedAt(LocalDateTime.now());
        return mapRepository.saveAndFlush(map);
    }

    private Map toEntity(MapCreateDto mapCreateDto) {
        Map map = new Map();
        map.setName(mapCreateDto.getName());
        return map;
    }

    public Map update(MapCreateDto mapCreateDto, Long id) {
        Map map = toEntity(mapCreateDto);
        map.setId(id);
        return mapRepository.saveAndFlush(map);
    }

    public List<Map> findTop5MapPlayed() {
        return mapRepository.findTop5MapPlayed();
    }
}