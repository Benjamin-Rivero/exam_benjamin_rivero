package fr.rivero.benjamin.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.rivero.benjamin.json_views.JsonViewRound;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewRound.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViewRound.Points.class)
    private Integer points;

    @Column(nullable = false)
    @JsonView(JsonViewRound.Time.class)
    private Integer time;

    @Column(nullable = false)
    @JsonView(JsonViewRound.Distance.class)
    private Long distance;

    @JsonView(JsonViewRound.CreatedAt.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonView(JsonViewRound.Game.class)
    private Game game;

    @ManyToOne
    @JsonView(JsonViewRound.Selected.class)
    private Coordinate selected;

    @ManyToOne
    @JsonView(JsonViewRound.Origin.class)
    private Coordinate origin;



}