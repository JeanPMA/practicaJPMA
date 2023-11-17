package com.diplomado.practicaJPMA.domain.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @SequenceGenerator(name = "rol_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_sequence")
    @Column(nullable = false)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> usuarios = new HashSet<>();

    public Rol() {
    }

    public Rol(String name, Set<User> usuarios) {
        this.name = name;
        this.usuarios = usuarios;
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

    public Set<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<User> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }
}
