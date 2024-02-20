package com.sarah.crudspring.enums.converters;

import java.util.stream.Stream;

import com.sarah.crudspring.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        if(category == null) {
            return null;
        }
        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Category.values())
            .filter(c -> c.getValue().equals(value)) //esse filtro retorna varios valores
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
    
}
