package controller;

import facade.impl.SpecialityFacadeImpl;
import lombok.Getter;
import lombok.Setter;
import model.Speciality;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class SpecialityController implements Serializable {
    private static final long serialVersionUID = 6230421169636259169L;

    @Inject
    private SpecialityFacadeImpl specialityFacade;

    @Setter @Getter
    private Speciality speciality = new Speciality();

    @Transactional
    public void createNewSpeciality() {
        specialityFacade.addSpeciality(speciality);
        speciality = new Speciality();
    }

    public List<Speciality> getAllSpecialities() {
        return specialityFacade.getAllSpecialities();
    }
}
