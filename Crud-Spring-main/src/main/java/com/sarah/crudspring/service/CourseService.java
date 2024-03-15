package com.sarah.crudspring.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sarah.crudspring.dto.CourseDTO;
import com.sarah.crudspring.dto.CoursePageDTO;
import com.sarah.crudspring.dto.mapper.CourseMapper;
import com.sarah.crudspring.exception.RecordNotFoundException;
import com.sarah.crudspring.model.Course;
import com.sarah.crudspring.repository.CourseRepository;


@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO list(int page, int pageSize) {
        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(page, pageSize));
        List<CourseDTO> courses = pageCourse.get().map(courseMapper::toDTO).collect(Collectors.toList());
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
    }
    
    // public List<CourseDTO> list() {
    //     return courseRepository.findAll()
    //         .stream()
    //         .map(courseMapper::toDTO)
    //         .collect(Collectors.toList());
    // }

    public CourseDTO findById(@PathVariable Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
    
    public CourseDTO create(CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }
    public CourseDTO update(Long id, CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseDTO.category());
                    // recordFound.setLessons(course.getLessons());
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(lesson -> recordFound.getLessons().add(lesson));
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    
    public void delete(@PathVariable Long id) {
        courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
