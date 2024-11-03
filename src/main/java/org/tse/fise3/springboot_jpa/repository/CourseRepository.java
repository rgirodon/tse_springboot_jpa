package org.tse.fise3.springboot_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tse.fise3.springboot_jpa.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
