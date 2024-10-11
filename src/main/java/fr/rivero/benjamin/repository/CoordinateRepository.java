package fr.rivero.benjamin.repository;

import fr.rivero.benjamin.entity.embedded.CoordinateId;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.rivero.benjamin.entity.Coordinate;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long /*CoordinateId*/> {
}