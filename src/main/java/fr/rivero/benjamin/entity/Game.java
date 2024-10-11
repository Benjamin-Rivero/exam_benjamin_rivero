package fr.rivero.benjamin.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.rivero.benjamin.json_views.JsonViewGame;
import net.datafaker.providers.base.Bool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewGame.Id.class)
    private String id;

    @JsonView(JsonViewGame.MaximumTime.class)
    private Integer maximumTime;

    @JsonView(JsonViewGame.HasMove.class)
    private Boolean hasMove;

    @JsonView(JsonViewGame.HasPan.class)
    private Boolean hasPan;

    @JsonView(JsonViewGame.HasZoom.class)
    private Boolean hasZoom;

    @JsonView(JsonViewGame.CreatedAt.class)
    private LocalDateTime createdAt;

    @JsonView(JsonViewGame.NbRounds.class)
    private Integer nbRounds;

    @ManyToOne
    @JsonView(JsonViewGame.Map.class)
    private Map map;

    @ManyToOne
    @JsonView(JsonViewGame.User.class)
    private User user;

    @OneToMany(mappedBy = "game")
    private List<Round> rounds = new ArrayList<>();

    @JsonView(JsonViewGame.TotalPoints.class)
    public Integer getTotalPoints(){
        return rounds.stream().mapToInt(Round::getPoints).sum();
    }



}