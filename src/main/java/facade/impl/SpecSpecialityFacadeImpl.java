package facade.impl;

import model.Speciality;
import model.Subject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class SpecSpecialityFacadeImpl extends SpecialityFacadeImpl {
    @Override
    public void addSpeciality(Speciality speciality) {
        speciality.setLength(4);
        super.addSpeciality(speciality);
    }
}
