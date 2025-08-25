package com.spectro.student_api.config;

import com.spectro.student_api.entity.Student;
import com.spectro.student_api.entity.User;
import com.spectro.student_api.repository.StudentRepository;
import com.spectro.student_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.spectro.student_api.entity.User.Role.ADMIN;
import static com.spectro.student_api.entity.User.Role.USER;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create default admin user
        if (!userRepository.existsByUsername("admin")) {
            Set<User.Role> adminRoles = new HashSet<>();
            adminRoles.add(ADMIN);

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(adminRoles)
                    .build();

            userRepository.save(admin);
            System.out.println("Default admin user created: admin/admin123");
        }

        // Create default regular user
        if (!userRepository.existsByUsername("user")) {
            Set<User.Role> userRoles = new HashSet<>();
            userRoles.add(USER);

            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user123"))
                    .roles(userRoles)
                    .build();

            userRepository.save(user);
            System.out.println("Default regular user created: user/user123");
        }

        // Create sample students if none exist
        if (studentRepository.count() == 0) {
            Student[] sampleStudents = {
                    Student.builder()
                            .firstName("أحمد")
                            .lastName("محمد")
                            .email("ahmed.mohamed@email.com")
                            .dateOfBirth(LocalDate.of(2000, 1, 15))
                            .build(),
                    Student.builder()
                            .firstName("فاطمة")
                            .lastName("علي")
                            .email("fatma.ali@email.com")
                            .dateOfBirth(LocalDate.of(1999, 3, 20))
                            .build(),
                    Student.builder()
                            .firstName("محمد")
                            .lastName("أحمد")
                            .email("mohamed.ahmed@email.com")
                            .dateOfBirth(LocalDate.of(2001, 6, 10))
                            .build(),
                    Student.builder()
                            .firstName("سارة")
                            .lastName("حسن")
                            .email("sara.hassan@email.com")
                            .dateOfBirth(LocalDate.of(1998, 9, 5))
                            .build(),
                    Student.builder()
                            .firstName("عمر")
                            .lastName("خالد")
                            .email("omar.khaled@email.com")
                            .dateOfBirth(LocalDate.of(2000, 11, 12))
                            .build(),
                    Student.builder()
                            .firstName("مريم")
                            .lastName("عبدالله")
                            .email("mariam.abdullah@email.com")
                            .dateOfBirth(LocalDate.of(1999, 2, 8))
                            .build(),
                    Student.builder()
                            .firstName("يوسف")
                            .lastName("إبراهيم")
                            .email("youssef.ibrahim@email.com")
                            .dateOfBirth(LocalDate.of(2001, 8, 25))
                            .build(),
                    Student.builder()
                            .firstName("نور")
                            .lastName("سالم")
                            .email("nour.salem@email.com")
                            .dateOfBirth(LocalDate.of(2000, 4, 18))
                            .build()
            };

            studentRepository.saveAll(Arrays.asList(sampleStudents));
            System.out.println("Sample students created successfully!");
        }
    }
}