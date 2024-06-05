package com.znaji.springdataaccesstasks.course.dao;

import com.znaji.springdataaccesstasks.course.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
@Transactional
public class JpaCourseDao implements CourseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Course store(Course course) {
        return entityManager.merge(course);
    }

    @Override
    public void delete(Long courseId) {
        var course = entityManager.find(Course.class, courseId);
        if (course != null) {
            entityManager.remove(course);
        }
    }

    @Override
    public Course findById(Long courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();

    }
}
