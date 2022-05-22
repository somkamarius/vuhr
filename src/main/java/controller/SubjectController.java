package controller;

import facade.impl.SubjectFacadeImpl;
import lombok.Getter;
import lombok.Setter;
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
public class SubjectController implements Serializable {
    private static final long serialVersionUID = 7494079547177391842L;

    @Inject
    private SubjectFacadeImpl subjectFacade;

    private Subject subject = new Subject();
    private Subject subjectToEdit = new Subject();

    @Transactional
    public String createNewSubject() {
        if (!subjectFacade.getAllSubjects().contains(subject.getName())) {
            subjectFacade.addSubject(subject);
            subject = new Subject();
        }
        return "index?faces-redirect=true";
    }

    public List<Subject> getAvailableSubjects() {
        return subjectFacade.getAllSubjects();
    }

    @Transactional
    public String updateSubject() {
        subjectFacade.updateSubject(subjectToEdit);
        return "index?faces-redirect=true";
    }

}
