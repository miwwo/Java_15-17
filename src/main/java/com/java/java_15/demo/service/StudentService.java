package com.java.java_15.demo.service;

import com.java.java_15.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class  StudentService implements TableService<Student>{
    @PersistenceContext
    private EntityManager entityManager;
    private Session session = null;
    @Transactional
    @Override
    public void create(Student student) {
        session = entityManager.unwrap(Session.class);
        session.persist(student);
    }

    @Override
    public List<Student> readAll() {
        session = entityManager.unwrap(Session.class);
        List<Student> students = session.createQuery("select i from Student i", Student.class).getResultList();
        session.close();
        return students;
    }

    @Override
    public Student read(Long id) {
        session = entityManager.unwrap(Session.class);
        Student student = session.createQuery(
                        "from Student where id = :id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
        session.close();
        return student;
    }

    @Transactional
    @Override
    public Boolean update(Student student, Long id) {
        session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Student set firstName=:fn, lastName=:ln where id=:id")
                .setParameter("id", id)
                .setParameter("fn", student.getFirstName())
                .setParameter("ln", student.getLastName());
        int status = query.executeUpdate();
        System.out.println(status);
        transaction.commit();
        return true;
    }
    @Transactional
    @Override
    public Boolean delete(Long id) {
        session = entityManager.unwrap(Session.class);
        Student student = session.get(Student.class, id);
        session.remove(student);
        return true;
    }
    public <T> List<Student> getStudentsFilteredBy(String attribute, T value){
        session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Predicate predicate = cb.equal(root.get(attribute), value);

        query.select(root).where(predicate);

        return session.createQuery(query).getResultList();
    }
}
