package at.htl.beeyond.resource;

import at.htl.beeyond.model.Application;
import at.htl.beeyond.repository.ApplicationRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/application")
@Transactional
public class ApplicationResource {

    @Inject
    ApplicationRepository applicationRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllApplications() {
        return Response.ok(this.applicationRepository.findAll().list()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getApplicationById(@PathParam("id") Long id) {
        Application application = this.applicationRepository.findById(id);
        if (application == null) {
            return Response.status(404).build();
        }

        return Response.ok(application).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadApplication(Application application) {
        this.applicationRepository.persistApplication(application);
        return Response.noContent().build();
    }
}
