package com.znaji.springdataaccesstasks.course.dao;

import com.znaji.springdataaccesstasks.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            var course = session.find(Course.class, courseId);
            if (course != null) {
                session.remove(course);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }


    }

    @Override
    public Course findById(Long courseId) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Course.class, courseId);
        }
    }

    @Override
    public List<Course> findAll() {
        try ( var session = sessionFactory.openSession()) {
            return  session.createQuery("SELECT c from Course c", Course.class).getResultList();
        }
    }
}
