package com.diplomado.practicaJPMA.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @SequenceGenerator(name = "user_detail_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_detail_sequence")
    @Column(nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 100, nullable = false)
    private String first_name;

    @Column(name = "last_name", length = 100, nullable = false)
    private String last_name;
    @Column(nullable = true)
    private Integer age;

    @Column(name = "birth_day", columnDefinition = "DATE", nullable = true)
    private LocalDate birth_day;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public UserDetail() {
    }

    public UserDetail(String first_name, String last_name, Integer age, LocalDate birth_day, Usuario usuario) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.birth_day = birth_day;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(LocalDate birth_day) {
        this.birth_day = birth_day;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", birth_day=" + birth_day +
                ", usuario=" + usuario +
                '}';
    }
}
