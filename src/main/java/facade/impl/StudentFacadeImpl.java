package facade.impl;

import model.Speciality;
import model.Student;
import model.Subject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentFacadeImpl {
    @Inject
    private EntityManager entityManager;
    @Inject
    private SpecialityFacadeImpl specialityFacade;
    @Inject
    private SubjectFacadeImpl subjectFacade;

    public void addStudent(Student student, Long specialityId, List<Long> subjectIds) {
        Student newStudent = buildStudent(student, specialityId, subjectIds);
        this.entityManager.persist(newStudent);
    }

    public Student buildStudent(Student student, Long specialityId, List<Long> subjectIds) {
        Speciality selectedSpeciality = specialityFacade.getSpecialityById(specialityId);
        student.setStudentSpeciality(selectedSpeciality);
        List<Subject> selectedSubjects = new ArrayList<>();
        for (Long id : subjectIds) {
            selectedSubjects.add(subjectFacade.getSubjectById(id));
        }
        if (!selectedSubjects.isEmpty()) {
            student.setSubjects(selectedSubjects);
        }
        return student;
    }


    public List<Student> getAllStudents() {
        return this.entityManager.createNamedQuery("Student.findAll", Student.class).getResultList();
    }
}
