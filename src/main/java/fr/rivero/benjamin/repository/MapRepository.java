package fr.rivero.benjamin.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fr.rivero.benjamin.entity.Map;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map,Long> {

    @Query("SELECT m FROM Map m JOIN Game g ON g.map = m GROUP BY m.id ORDER BY COUNT(*) DESC")
    List<Map> findTop5MapPlayed();
}