package com.diplomado.practicaJPMA.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_rol")
public class UserRol {

    @Id
    @SequenceGenerator(name = "userRol_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRol_sequence")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean active;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    public UserRol() {

    }

    public UserRol(Boolean active, LocalDateTime created_at, User user, Rol rol) {
        this.active = active;
        this.created_at = created_at;
        this.user = user;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UserRol{" +
                "id=" + id +
                ", active=" + active +
                ", created_at=" + created_at +
                ", user=" + user +
                ", rol=" + rol +
                '}';
    }
}
