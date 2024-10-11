package fr.rivero.benjamin.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fr.rivero.benjamin.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    @Query("SELECT g,SUM(r.points) FROM Game g JOIN Round r ON r.game = g GROUP BY g.id ORDER BY SUM(r.points) DESC LIMIT 10")
    List<Game> findTop10Score();

    List<Game> findTop10ByOrderByCreatedAtDesc();
}