package facade.impl;

import model.Subject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class SubjectFacadeImpl {
    @Inject
    private EntityManager entityManager;

    public void addSubject(Subject subject) {
        this.entityManager.persist(subject);
    }

    public List<Subject> getAllSubjects() {
        return this.entityManager.createNamedQuery("Subject.findAll", Subject.class).getResultList();
    }

    public Subject getSubjectById(Long id) {
        TypedQuery<Subject> namedQuery = this.entityManager.createNamedQuery("Subject.getById", Subject.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    public void updateSubject(Subject subjectToEdit) {
        this.entityManager.merge(subjectToEdit);
    }
}
