package fr.rivero.benjamin.json_views;

public class JsonViewCoordinate {

    public interface CoordinateShow extends Id,Latitude,Longitude {}

    public interface Longitude {}
    public interface Latitude {}

    public interface Id {
    }
}