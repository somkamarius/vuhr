package rest;

import facade.impl.SubjectFacadeImpl;
import lombok.Getter;
import lombok.Setter;
import model.Student;
import model.Subject;
import rest.contracts.SubjectDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/speciality")
public class SubjectController {
    @Inject
    @Setter
    @Getter
    private SubjectFacadeImpl subjectDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Subject subject = subjectDAO.getSubjectById(id);
        if (subject == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setName(subject.getName());
        subjectDTO.setLecturer(subject.getLecturer());
        subjectDTO.setDescription(subject.getDescription());
        subjectDTO.setCreditAmount(subject.getCreditAmount());

        return Response.ok(subjectDTO).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(SubjectDTO subjectData) {
        Subject subject = new Subject();
        subject.setName(subjectData.getName());
        subject.setLecturer(subjectData.getLecturer());
        subject.setDescription(subjectData.getDescription());
        subject.setCreditAmount(subjectData.getCreditAmount());
        subjectDAO.addSubject(subject);
        return Response.ok().build();
    }


    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long id,
            SubjectDTO subjectData) {
        try {
            Subject existingSubject = subjectDAO.getSubjectById(id);
            if (existingSubject == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingSubject.setName(subjectData.getName());
            existingSubject.setLecturer(subjectData.getLecturer());
            existingSubject.setDescription(subjectData.getDescription());
            existingSubject.setCreditAmount(subjectData.getCreditAmount());
            subjectDAO.updateSubject(existingSubject);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
