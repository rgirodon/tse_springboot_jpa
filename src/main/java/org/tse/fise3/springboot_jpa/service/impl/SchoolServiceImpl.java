package org.tse.fise3.springboot_jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tse.fise3.springboot_jpa.model.Course;
import org.tse.fise3.springboot_jpa.model.CulturalOption;
import org.tse.fise3.springboot_jpa.model.Student;
import org.tse.fise3.springboot_jpa.repository.CourseRepository;
import org.tse.fise3.springboot_jpa.repository.CulturalOptionRepository;
import org.tse.fise3.springboot_jpa.repository.StudentRepository;
import org.tse.fise3.springboot_jpa.service.SchoolService;

@Transactional
@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CulturalOptionRepository culturalOptionRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional(readOnly = true)
	public List<CulturalOption> findAllCulturalOptions() {
		
		return this.culturalOptionRepository.findAll();
	}

	@Override
	public void deleteCulturalOption(CulturalOption culturalOption) {
		
		this.culturalOptionRepository.delete(culturalOption);
	}

	@Override
	public void persistCulturalOption(CulturalOption option) {
		
		this.culturalOptionRepository.save(option);
	}

	@Override
	public void persistCourse(Course course) {
		
		this.courseRepository.save(course);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAllCourses() {
		
		return this.courseRepository.findAll();
	}

	@Override
	public void deleteCourse(Course course) {
		
		this.courseRepository.delete(course);
	}

	@Override
	@Transactional(readOnly = true)
	public CulturalOption findCulturalOptionById(long id) {
		
		return this.culturalOptionRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Course findCourseById(long id) {
		
		return this.courseRepository.findById(id).orElse(null);
	}

	@Override
	public void persistStudent(Student student) {
		
		this.studentRepository.save(student);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> findAllStudents() {
		
		return this.studentRepository.findAll();
	}

	@Override
	public void deleteStudent(Student student) {
		
		this.studentRepository.delete(student);
	}
	
	
}
