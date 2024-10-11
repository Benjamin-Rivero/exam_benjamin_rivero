package fr.rivero.benjamin.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.rivero.benjamin.json_views.JsonViewMap;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewMap.Id.class)
    private Long id;

    @JsonView(JsonViewMap.Name.class)
    private String name;

    @JsonView(JsonViewMap.CreatedAt.class)
    private LocalDateTime createdAt;



}