package controller;

import facade.impl.SubjectFacadeImpl;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import model.Subject;

@ViewScoped
@Named
@Getter
@Setter
public class DetailedSubjectController implements Serializable {
    private static final long serialVersionUID = 8709909428562555018L;
    @Inject
    SubjectFacadeImpl subjectFacade;

    private Subject subject;
    private String name;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer subjectId = Integer.parseInt(requestParameters.get("subjectId"));
        this.subject = subjectFacade.getSubjectById(subjectId.longValue());
    }

    @Transactional
    public String updateSubject() {
        try {
            subject.setName(name);

            subjectFacade.updateSubject(subject);

        }  catch (OptimisticLockException e) {
            e.printStackTrace();
            return "subject.xhtml?faces-redirect=true&subjectId=" + subject.getId() + "&error=optimistic-lock-exception";
        }

        return "subject.xhtml?subjectId=" + subject.getId()  + "&faces-redirect=true";
    }


}