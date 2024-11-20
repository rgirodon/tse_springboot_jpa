package org.tse.fise3.springboot_jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tse.fise3.springboot_jpa.model.Course;
import org.tse.fise3.springboot_jpa.model.CulturalOption;
import org.tse.fise3.springboot_jpa.model.Student;
import org.tse.fise3.springboot_jpa.model.Teacher;
import org.tse.fise3.springboot_jpa.service.SchoolService;

@SpringBootTest
public class SchoolServiceTest {

	@Autowired
	private SchoolService schoolService;
	
	@BeforeEach
	public void setup() {
		
		this.createDefaultCulturalOptions();
		
		this.createDefaultCourses();
	}

	@AfterEach
	public void destroy() {
		
		this.deleteDefaultCulturalOptions();
		
		this.deleteDefaultCourses();
	}
	
	private void deleteDefaultCulturalOptions() {
		List<CulturalOption> culturalOptions = this.schoolService.findAllCulturalOptions();
		for (CulturalOption culturalOption : culturalOptions) {
			this.schoolService.deleteCulturalOption(culturalOption);
		}
	}

	private void createDefaultCulturalOptions() {
		
		CulturalOption option1 = new CulturalOption();
		option1.setName("Painting");
		this.schoolService.persistCulturalOption(option1);
		
		CulturalOption option2 = new CulturalOption();
		option2.setName("Theater");
		this.schoolService.persistCulturalOption(option2);
		
		CulturalOption option3 = new CulturalOption();
		option3.setName("Choral");
		this.schoolService.persistCulturalOption(option3);		
	}
	
	private void createDefaultCourses() {
		
		Teacher teacher1 = new Teacher();
		teacher1.setName("Christophe Gravier");
		this.schoolService.persistTeacher(teacher1);
		
		Teacher teacher2 = new Teacher();
		teacher2.setName("Kamal Singh");
		this.schoolService.persistTeacher(teacher2);
		
		Course course1 = new Course();
		course1.setName("Computer Science");
		course1.setTeacher(teacher1);
		this.schoolService.persistCourse(course1);
		
		Course course2 = new Course();
		course2.setName("Network");
		course2.setTeacher(teacher2);
		this.schoolService.persistCourse(course2);
		
		Course course3 = new Course();
		course3.setName("Data Science");
		course3.setTeacher(teacher1);
		this.schoolService.persistCourse(course3);		
	}
	
	private void deleteDefaultCourses() {
		List<Course> courses = this.schoolService.findAllCourses();
		for (Course course : courses) {
			this.schoolService.deleteCourse(course);
		}
		
		List<Teacher> teachers = this.schoolService.findAllTeachers();
		for (Teacher teacher : teachers) {
			this.schoolService.deleteTeacher(teacher);
		}
	}
		
	@Test
	public void persistStudentThenRetrieveTheDetails() {
		
		CulturalOption option1 = this.schoolService.findCulturalOptionById(1L);
		Course course1 = this.schoolService.findCourseById(1L);
		Course course2 = this.schoolService.findCourseById(2L);
		
		Student studentToCreate = new Student();
		studentToCreate.setName("Rémy Girodon");
		
		option1.addStudent(studentToCreate);
		
		course1.addStudent(studentToCreate);
		course2.addStudent(studentToCreate);
		
		this.schoolService.persistStudent(studentToCreate);
		
		List<Student> students = this.schoolService.findAllStudents();
		assertEquals(1, students.size());
		
		Student studentFound = students.get(0);
		assertEquals(1L, studentFound.getId());
		assertEquals("Rémy Girodon", studentFound.getName());
		assertEquals("Painting", studentFound.getCulturalOption().getName());
		assertEquals(2, studentFound.getCourses().size());
		
		boolean networkFound = false;
		
		for (Course studentCourse : studentFound.getCourses()) {
			
			if ("Network".equals(studentCourse.getName())) {
				
				networkFound = true;
				
				assertEquals("Kamal Singh", studentCourse.getTeacher().getName());
			}
		}
		
		if (!networkFound) {
			fail("Student not inscribed to Network course !");
		}
		
		this.schoolService.deleteStudent(studentFound);
		
		students = this.schoolService.findAllStudents();
		assertEquals(0, students.size());
	}
}
