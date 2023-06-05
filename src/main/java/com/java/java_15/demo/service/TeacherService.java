package com.java.java_15.demo.service;

import com.java.java_15.demo.entity.Teacher;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService implements TableService<Teacher>{
    @PersistenceContext
    private EntityManager entityManager;
    private Session session;

    @Transactional
    @Override
    public void create(Teacher teacher) {
        session = entityManager.unwrap(Session.class);
        session.persist(teacher);
        session.close();
    }

    @Override
    public List<Teacher> readAll() {
        session = entityManager.unwrap(Session.class);
        List<Teacher> teachers = session.createQuery("select i from Teacher i", Teacher.class).getResultList();
        session.close();
        return teachers;
    }

    @Override
    public Teacher read(Long id) {
        session = entityManager.unwrap(Session.class);
        Teacher teacher = session.createQuery(
                "from Teacher where id = :id", Teacher.class)
                .setParameter("id", id)
                .getSingleResult();
        session.close();
        return teacher;
    }

    @Transactional
    @Override
    public Boolean update(Teacher o, Long id) {
        session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Teacher set firstName=:fn, lastName=:ln, subject=:s where id=:id")
                .setParameter("id", id)
                .setParameter("fn", o.getFirstName())
                .setParameter("ln", o.getLastName())
                .setParameter("s", o.getSubject());
        int status = query.executeUpdate();
        System.out.println(status);
        transaction.commit();
        session.close();
        return true;
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        session = entityManager.unwrap(Session.class);
        Teacher teacher = session.get(Teacher.class, id);
        session.remove(teacher);
        session.close();
        return true;
    }
    public <T> List<Teacher> getTeachersFilteredBy(String attribute, T value){
        session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);
        Predicate predicate = cb.equal(root.get(attribute), value);

        query.select(root).where(predicate);

        return session.createQuery(query).getResultList();
    }
}
