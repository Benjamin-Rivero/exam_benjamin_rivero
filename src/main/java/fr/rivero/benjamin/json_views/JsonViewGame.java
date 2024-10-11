package fr.rivero.benjamin.json_views;

public class JsonViewGame {

    public interface GameMinimal extends Id,CreatedAt,NbRounds,TotalPoints {}

    public interface GameList extends JsonViews.Page,JsonViewUser.Username, JsonViewUser.Id, JsonViewUser.Level,GameMinimal {}

    public interface GameShow extends GameMinimal, MaximumTime,HasMove,HasPan,HasZoom, JsonViewRound.Points, JsonViewRound.Time, JsonViewRound.Distance {}

    public interface NbRounds {}

    public interface CreatedAt {}

    public interface HasZoom {}
    public interface HasPan {}
    public interface HasMove {}
    public interface MaximumTime {}

    public interface Map {
    }

    public interface User {
    }

    public interface Id {
    }

    public interface TotalPoints {
    }
}