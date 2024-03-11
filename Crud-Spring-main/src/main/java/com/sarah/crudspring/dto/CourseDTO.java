package com.sarah.crudspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sarah.crudspring.dto.mapper.LessonDTO;

public record CourseDTO(
    @JsonProperty("_id") Long id, 
    String name, 
    String category,
    List<LessonDTO> lessons) {
    
}
