package fr.rivero.benjamin.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.rivero.benjamin.json_views.JsonViewUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewUser.Id.class)
    private String id;

    @JsonView(JsonViewUser.Username.class)
    private String username;

    @JsonView(JsonViewUser.Email.class)
    private String email;

    @JsonView(JsonViewUser.Password.class)
    private String password;

    @Column(nullable = false)
    @JsonView(JsonViewUser.Avatar.class)
    private String avatar;

    @JsonView(JsonViewUser.BirthedAt.class)
    private LocalDate birthedAt;

    @JsonView(JsonViewUser.CreatedAt.class)
    private LocalDateTime createdAt;

    @JsonView(JsonViewUser.Level.class)
    private Integer level;

    @JsonView(JsonViewUser.Roles.class)
    private String roles;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewUser.Games.class)
    private List<Game> games = new ArrayList<>();

    @JsonView(JsonViewUser.IsAdmin.class)
    public boolean isAdmin() {
        return roles.contains("ADMIN");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}