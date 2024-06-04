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
        System.out.println("test rest of jpa methods");
        var courses = courseDao.findAll();
        courses.forEach(System.out::println);
        courseDao.delete(3L);
        courses.forEach(System.out::println);
    }
}
