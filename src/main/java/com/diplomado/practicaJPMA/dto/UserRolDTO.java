package com.diplomado.practicaJPMA.dto;

import com.diplomado.practicaJPMA.domain.entities.Rol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;

import java.time.LocalDateTime;

public class UserRolDTO {
    private Long id;
    private Boolean active;
    private LocalDateTime created_at;
    private Usuario usuario;
    private Rol rol;

    public UserRolDTO() {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UserRolDTO{" +
                "id=" + id +
                ", active=" + active +
                ", created_at=" + created_at +
                ", usuario=" + usuario +
                ", rol=" + rol +
                '}';
    }
}
