package com.sarah.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sarah.crudspring.exception.RecordNotFoundException;
import com.sarah.crudspring.model.Course;
import com.sarah.crudspring.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public @RequestMapping List<Course> list() {
        return courseRepository.findAll();
    }

    public Course findById(@PathVariable Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Long id, Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    
    public void delete(@PathVariable Long id) {
        courseRepository.delete(
            courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));


    }

}
