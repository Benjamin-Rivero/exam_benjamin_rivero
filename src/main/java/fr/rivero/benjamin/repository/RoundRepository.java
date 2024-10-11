package fr.rivero.benjamin.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.rivero.benjamin.entity.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
}