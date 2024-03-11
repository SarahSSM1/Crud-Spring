package com.sarah.crudspring.dto.mapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sarah.crudspring.dto.CourseDTO;
import com.sarah.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        List<LessonDTO> lessons = course.getLessons()
        .stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
        .collect(Collectors.toList());
        return new CourseDTO(course.getId(), course.getName(), course.getCategory(), 
        lessons);
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
