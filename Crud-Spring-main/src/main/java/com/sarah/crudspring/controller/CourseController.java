package com.sarah.crudspring.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sarah.crudspring.dto.CourseDTO;
import com.sarah.crudspring.dto.CoursePageDTO;
import com.sarah.crudspring.service.CourseService;


// @Validated
@RestController
@RequestMapping("/api/courses")

public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @RequestMapping CoursePageDTO list( int page,  int pageSize) {
        return courseService.list(page, pageSize);
    }

    // @GetMapping
    // public @RequestMapping List<CourseDTO> list() {
    //     return courseService.list();
    // }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody CourseDTO course) {
        return courseService.create(course);
    }

    @PutMapping("{id}")
    public CourseDTO update(@PathVariable Long id, @RequestBody CourseDTO course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       courseService.delete(id);
    }
}
