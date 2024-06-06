package com.znaji.springdataaccesstasks.course.dao;

import com.znaji.springdataaccesstasks.course.entity.Course;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface CourseRepository extends JpaRepository<Course, Long>{
}
