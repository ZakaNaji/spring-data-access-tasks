package com.znaji.springdataaccesstasks.course.dao;

import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateCourseDao implements CourseDao{
    private final SessionFactory sessionFactory;

    @Override
    public Course store(Course course) {
        var session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            if (course.getId() == null) {
                session.persist(course);
            } else {
                session.merge(course);
            }
            session.getTransaction().commit();
            return course;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long courseId) {

    }

    @Override
    public Course findById(Long courseId) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }
}
