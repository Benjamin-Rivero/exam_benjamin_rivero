package fr.rivero.benjamin.service;

import fr.rivero.benjamin.dto.CoordinateCreateDto;
import fr.rivero.benjamin.entity.embedded.CoordinateId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.rivero.benjamin.entity.Coordinate;
import fr.rivero.benjamin.repository.CoordinateRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CoordinateService {

    private final CoordinateRepository coordinateRepository;

    public Coordinate findById(Long /*CoordinateId*/ id) {
        return coordinateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Coordinate> list() {
        return coordinateRepository.findAll();
    }

    public Coordinate create(CoordinateCreateDto coordinateCreateDto){
        return coordinateRepository.saveAndFlush(toEntity(coordinateCreateDto));
    }

    private Coordinate toEntity(CoordinateCreateDto coordinateCreateDto) {
        Coordinate coordinate = new Coordinate();
        /*CoordinateId coordinateId = new CoordinateId(coordinate.getLatitude(),coordinateCreateDto.getLongitude());
        coordinate.setId(coordinateId);*/
        coordinate.setLatitude(coordinateCreateDto.getLatitude());
        coordinate.setLongitude(coordinateCreateDto.getLongitude());
        return coordinate;
    }

}