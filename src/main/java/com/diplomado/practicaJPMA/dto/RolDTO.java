package com.diplomado.practicaJPMA.dto;

import com.diplomado.practicaJPMA.domain.entities.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RolDTO {

    private Integer id;
    private String name;
    private List<String> nombresUsuarios;

    public RolDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNombresUsuarios() {
        return nombresUsuarios;
    }

    public void setNombresUsuarios(List<String> nombresUsuarios) {
        this.nombresUsuarios = nombresUsuarios;
    }

    @Override
    public String toString() {
        return "RolDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nombresUsuarios=" + nombresUsuarios +
                '}';
    }
}
