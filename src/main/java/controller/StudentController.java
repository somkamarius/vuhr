package controller;

import facade.impl.StudentFacadeImpl;
import lombok.Getter;
import lombok.Setter;
import model.Student;
import model.Subject;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
@Getter
@Setter
public class StudentController implements Serializable {
    private static final long serialVersionUID = 5550441667169227679L;

    @Inject
    private StudentFacadeImpl studentFacade;

    private Student student = new Student();
    private Long specialityId;
    private List<Long> subjectIds;

    @Transactional
    public String createStudent() {
        studentFacade.addStudent(student, specialityId, subjectIds);
        student = new Student();
        return "index?faces-redirect=true";
    }

    public List<Student> getStudents() {
        return studentFacade.getAllStudents();
    }

    public String changeEmail(String email) {
        if(email == null || email.length() == 0){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(email.charAt(0));
        for(int i = 1; i < email.length(); i++){
            stringBuilder.append('*');
            if(email.charAt(i) == '@'){
                stringBuilder.append(email.substring(i));
                break;
            }
        };
        return stringBuilder.toString();
    }
}
