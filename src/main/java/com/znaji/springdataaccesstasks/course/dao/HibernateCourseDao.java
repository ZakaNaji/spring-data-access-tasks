package com.znaji.springdataaccesstasks.course.dao;

import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("hibernate")
@RequiredArgsConstructor

public class HibernateCourseDao implements CourseDao {
    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public Course store(Course course) {
        var session = sessionFactory.getCurrentSession();
        if (course.getId() == null) {
            session.persist(course);
        } else {
            session.merge(course);
        }
        return course;
    }

    @Override
    @Transactional
    public void delete(Long courseId) {
        var session = sessionFactory.getCurrentSession();
        var course = session.find(Course.class, courseId);
        if (course != null) {
            session.remove(course);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long courseId) {
        var session = sessionFactory.getCurrentSession();
        return session.find(Course.class, courseId);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        var session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT c from Course c", Course.class).getResultList();
    }
}
