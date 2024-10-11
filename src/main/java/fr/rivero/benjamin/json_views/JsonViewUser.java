package fr.rivero.benjamin.json_views;

public class JsonViewUser {

    public interface UserShowView extends Id,Username,Email,Avatar,BirthedAt,CreatedAt,Level,IsAdmin {}

    public interface Roles {}
    public interface Level {}
    public interface CreatedAt {}
    public interface BirthedAt {}

    public interface Avatar {}
    public interface Password {}
    public interface Email {}

    public interface Username {}

    public interface Games {
    }

    public interface Id {
    }

    public interface IsAdmin {
    }
}