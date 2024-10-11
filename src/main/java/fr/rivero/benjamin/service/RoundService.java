package fr.rivero.benjamin.service;

import fr.rivero.benjamin.dto.RoundCreateDto;
import fr.rivero.benjamin.entity.Coordinate;
import fr.rivero.benjamin.repository.CoordinateRepository;
import fr.rivero.benjamin.util.DistanceCalc;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.rivero.benjamin.entity.Round;
import fr.rivero.benjamin.repository.RoundRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Repository
@AllArgsConstructor
public class RoundService {

    private final RoundRepository roundRepository;
    private final CoordinateRepository coordinateRepository;
    private final GameService gameService;

    public Round findById(Long id) {
        return roundRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Round> list() {
        return roundRepository.findAll();
    }

    public Round create(RoundCreateDto roundCreateDto){
        Round round = toEntity(roundCreateDto);
        round.setCreatedAt(LocalDateTime.now());
        Random random = new Random();
        List<Coordinate> coordinates = coordinateRepository.findAll();
        Coordinate coordinate = coordinates.get(random.nextInt(0, (coordinates.size() - 1)));
        return roundRepository.saveAndFlush(round);
    }

    private Round toEntity(RoundCreateDto roundCreateDto) {
        Round round = new Round();
        round.setGame(gameService.findById(roundCreateDto.getGameid()));
        return round;
    }

    public Integer calcPoints(Long id){
        Round round = findById(id);
        float lat2 = Float.parseFloat(round.getOrigin().getLatitude().replace(",","."));
        float lat1 = Float.parseFloat(round.getSelected().getLatitude().replace(",","."));
        float lon2 = Float.parseFloat(round.getOrigin().getLongitude().replace(",","."));
        float lon1 = Float.parseFloat(round.getSelected().getLongitude().replace(",","."));
        int distance = (int)Math.ceil(DistanceCalc.distance(lat1,lat2,lon1,lon2));
        System.out.println(distance);
        if(distance > 10000000) return 0;
        if(distance == 0) return 5000;
        return 5000 - ((distance /10000000)*5000);
    }

}