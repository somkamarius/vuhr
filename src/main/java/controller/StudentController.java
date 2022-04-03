package controller;

import facade.impl.StudentFacadeImpl;
import lombok.Getter;
import lombok.Setter;
import model.Student;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

@ViewScoped
@Named
@Getter
@Setter
public class StudentController implements Serializable {
    private static final long serialVersionUID = 5550441667169227679L;

    @Inject
    private StudentFacadeImpl studentFacade;

    private Student student = new Student();

    @Transactional
    public String createStudent() {
        studentFacade.addStudent(student, "testName", "testSurname");
        student = new Student();
        return "index?faces-redirect=true";
    }
}
