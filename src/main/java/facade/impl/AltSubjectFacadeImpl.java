package facade.impl;

import model.Subject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class AltSubjectFacadeImpl extends SubjectFacadeImpl {
    @Override
    public void addSubject(Subject subject) {
        subject.setCreditAmount(5);
        super.addSubject(subject);
    }
}
