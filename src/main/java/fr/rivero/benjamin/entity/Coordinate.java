package fr.rivero.benjamin.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.entity.embedded.CoordinateId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.rivero.benjamin.json_views.JsonViewCoordinate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewCoordinate.Id.class)
    private Long id;

    /*@EmbeddedId
    private CoordinateId id;*/

    @JsonView(JsonViewCoordinate.Latitude.class)
//    @MapsId("latitude")
    private String latitude;

    @JsonView(JsonViewCoordinate.Longitude.class)
//    @MapsId("longitude")
    private String longitude;



}