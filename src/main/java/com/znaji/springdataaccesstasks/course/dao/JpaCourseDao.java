package com.znaji.springdataaccesstasks.course.dao;

import com.znaji.springdataaccesstasks.course.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaCourseDao implements CourseDao{

    private final EntityManagerFactory entityManagerFactory;
    @Override
    public Course store(Course course) {
        var em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = null;
        try (em) {
            tx = em.getTransaction();
            tx.begin();
            em.merge(course);
            tx.commit();
            return course;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long courseId) {
        var em = entityManagerFactory.createEntityManager();
        var tx = em.getTransaction();
        try (em) {
            tx.begin();
            var course = em.find(Course.class, courseId);
            if (course != null) {
                em.remove(course);
            }
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Course findById(Long courseId) {
        var em = entityManagerFactory.createEntityManager();
        try (em) {
            return em.find(Course.class, courseId);
        }
    }

    @Override
    public List<Course> findAll() {
        var em = entityManagerFactory.createEntityManager();
        try (em) {
            return em.createQuery("select c from Course c", Course.class).getResultList();
        }
    }
}
