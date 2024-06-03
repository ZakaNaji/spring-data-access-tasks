package com.znaji.springdataaccesstasks.course.cmd.runner;

import com.znaji.springdataaccesstasks.course.dao.CourseDao;
import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
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
        System.out.println("test delete");
        var course = courseDao.findById(1L);
        if (course != null) {
            courseDao.delete(1L);
        }
    }
}
