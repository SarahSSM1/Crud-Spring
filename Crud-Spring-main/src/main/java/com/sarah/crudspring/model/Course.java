package com.sarah.crudspring.model;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") // caso queira adicionar um valor que nao esteja na tabela
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course") //orphanRemoval é usada para remover uma entidade filha que não é  refeernciada pelofquando uma entidade filha não é referenciada pela entidade pai, ela será considerada "órfã" e removida do banco de dados.
    // @JoinColumn(name = "course_id")
    private List<Lesson> lessons = new ArrayList<>();
}
