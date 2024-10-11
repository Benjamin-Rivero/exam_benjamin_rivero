package fr.rivero.benjamin.json_views;

public class JsonViewMap {

    public interface MapListView extends JsonViews.Page,Id,Name {}

    public interface MapShowView extends MapListView,CreatedAt {}

    public interface CreatedAt {}
    public interface Name {}

    public interface Id {
    }
}