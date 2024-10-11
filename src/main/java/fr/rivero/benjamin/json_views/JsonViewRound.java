package fr.rivero.benjamin.json_views;

public class JsonViewRound {

    public interface RoundMinimal extends Points,Time,Distance {}

    public interface RoundShow extends Id,CreatedAt,RoundMinimal {}

    public interface CreatedAt {}
    public interface Distance {}
    public interface Time {}
    public interface Points {}

    public interface Game {
    }

    public interface Selected {
    }

    public interface Origin {
    }

    public interface Id {
    }
}