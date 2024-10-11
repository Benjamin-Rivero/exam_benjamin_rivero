package fr.rivero.benjamin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameCreateDto {

    private Boolean hasPan;

    private Boolean hasZoom;

    private Boolean hasMove;

    private Integer maximumTime;

    private Integer rounds;

    private Long mapId;

}