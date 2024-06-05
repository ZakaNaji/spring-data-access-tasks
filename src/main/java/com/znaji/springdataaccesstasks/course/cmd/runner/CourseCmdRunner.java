package com.znaji.springdataaccesstasks.course.cmd.runner;

import com.znaji.springdataaccesstasks.course.dao.CourseDao;
import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseCmdRunner implements CommandLineRunner {

    private final CourseDao courseDao;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test rest of Hibernate methods");
        courseDao.store(Course.builder().title("Java for Dummies").beginDate(LocalDate.now()).endDate(LocalDate.now().plusMonths(3)).fee(1000).build());
        var courses = courseDao.findAll();
        courses.forEach(System.out::println);

    }
}
