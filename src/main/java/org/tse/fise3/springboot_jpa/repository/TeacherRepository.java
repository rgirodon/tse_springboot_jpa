package org.tse.fise3.springboot_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tse.fise3.springboot_jpa.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
