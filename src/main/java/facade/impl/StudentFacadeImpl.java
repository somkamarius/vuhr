package facade.impl;

import model.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudentFacadeImpl {
    @Inject
    private EntityManager entityManager;

    public void addStudent(Student student, String name, String surname) {
//        Student newStudent = new Student(name, surname);
        this.entityManager.persist(student);
    }
}
