package com.sarah.crudspring.dto.mapper;
import org.springframework.stereotype.Component;

import com.sarah.crudspring.dto.CourseDTO;
import com.sarah.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.id() != null){
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        return course;
    }
}
