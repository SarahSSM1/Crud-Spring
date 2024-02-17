package com.sarah.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
// @Table( name = "cursos") mudar o nome da tabela casos venha do legado e a tabela ja tenha um nome
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") // caso queira adicionar um valor que nao esteja na tabela
    private Long id;

    // @NotBlank
    // @NotNull
    // @Size(min = 5, max = 100)
    @Column(length = 200, nullable = false)
    private String name;

    // @NotNull
    // @Size(max = 10)
    // @Pattern(regexp = "Back-end | Front-end")
    @Column(length = 10, nullable = false)
    private String category;

    
}
