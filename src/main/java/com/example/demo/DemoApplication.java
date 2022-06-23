package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository
                             //, MongoTemplate mongoTemplate
    ) {
        return args -> {
            Address address = new Address(
                    "India", "Lucknow", "226017"
            );
            String email = "nikgupta@gmail.com";
            Student student = new Student(
                    "Nikita",
                    "Gupta", email,
                    Gender.Female,
                    address,
                    List.of("computer science"),
                    BigDecimal.TEN,
                    LocalDateTime.now()

            );

            // methodQueryAndTemplate(repository, mongoTemplate, email, student);
            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> System.out.println("Student already Exists!\n" + s),
                    () -> {
                        System.out.println("Inserting student1!\n" + student);
                        repository.insert(student);
                    });
        };
    }

//	private void methodQueryAndTemplate(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("email").is(email));
//
//		List<Student> students = mongoTemplate.find(query, Student.class);
//
//		if(students.size()>1){
//			throw new IllegalStateException("Found student with email " + email);
//		}
//
//		if(students.isEmpty()) {
//			System.out.println("Inserting student :\n" + student);
//			repository.insert(student);
//		}
//		else{
//			System.out.println("Already Exists!");
//		}
//	}
}
