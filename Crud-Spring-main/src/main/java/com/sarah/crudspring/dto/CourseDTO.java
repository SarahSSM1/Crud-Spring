package com.sarah.crudspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CourseDTO(
    @JsonProperty("_id") Long id, 
    String name, 
    String category) {
    
}
