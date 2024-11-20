package org.tse.fise3.springboot_jpa.service;

import java.util.List;

import org.tse.fise3.springboot_jpa.model.Course;
import org.tse.fise3.springboot_jpa.model.CulturalOption;
import org.tse.fise3.springboot_jpa.model.Student;
import org.tse.fise3.springboot_jpa.model.Teacher;

public interface SchoolService {

	List<CulturalOption> findAllCulturalOptions();

	void deleteCulturalOption(CulturalOption culturalOption);

	void persistCulturalOption(CulturalOption option);

	void persistCourse(Course course);

	List<Course> findAllCourses();

	void deleteCourse(Course course);

	CulturalOption findCulturalOptionById(long id);

	Course findCourseById(long id);

	void persistStudent(Student student);

	List<Student> findAllStudents();

	void deleteStudent(Student student);

	void persistTeacher(Teacher teacher);

	List<Teacher> findAllTeachers();

	void deleteTeacher(Teacher teacher);

}
