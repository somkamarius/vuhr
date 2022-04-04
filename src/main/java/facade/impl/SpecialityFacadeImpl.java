package facade.impl;

import model.Speciality;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class SpecialityFacadeImpl {
    @Inject
    private EntityManager entityManager;

    public void addSpeciality(Speciality speciality) {
        this.entityManager.persist(speciality);
    }

    public List<Speciality> getAllSpecialities() {
        return this.entityManager.createNamedQuery("Speciality.findAll", Speciality.class).getResultList();
    }

    public Speciality getSpecialityById(Long id) {
        TypedQuery<Speciality> namedQuery = this.entityManager.createNamedQuery("Speciality.findById", Speciality.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }
}
