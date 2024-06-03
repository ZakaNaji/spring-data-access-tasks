package com.znaji.springdataaccesstasks.course.cmd.runner;

import com.znaji.springdataaccesstasks.course.dao.CourseDao;
import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseCmdRunner implements CommandLineRunner {

    private final CourseDao courseDao;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test insert course here...");
        var course = Course.builder()
                .title("Java Programming")
                .beginDate(java.time.LocalDate.now())
                .endDate(java.time.LocalDate.now().plusMonths(3))
                .fee(1000)
                .build();
        courseDao.store(course);

    }
}
