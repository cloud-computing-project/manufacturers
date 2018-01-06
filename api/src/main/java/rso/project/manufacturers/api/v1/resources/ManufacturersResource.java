package rso.project.manufacturers.api.v1.resources;

import rso.project.manufacturers.Manufacturer;
import rso.project.manufacturers.services.ManufacturersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/manufacturers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManufacturersResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ManufacturersBean manufacturersBean;

    @GET
    public Response getManufacturers() {

        List<Manufacturer> manufacturers = manufacturersBean.getManufacturers(uriInfo);

        return Response.ok(manufacturers).build();
    }

    @GET
    @Path("/{manufacturerId}")
    public Response getManufacturer(@PathParam("manufacturerId") String manufacturerId) {

        Manufacturer manufacturer = manufacturersBean.getManufacturer(manufacturerId);

        if (manufacturer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(manufacturer).build();
    }

    @POST
    public Response createManufacturer(Manufacturer manufacturer) {

        if (manufacturer.getTitle() == null || manufacturer.getTitle().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            manufacturer = manufacturersBean.createManufacturer(manufacturer);
        }

        if (manufacturer.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(manufacturer).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(manufacturer).build();
        }
    }

    @PUT
    @Path("{manufacturerId}")
    public Response putManufacturer(@PathParam("manufacturerId") String manufacturerId, Manufacturer manufacturer) {

        manufacturer = manufacturersBean.putManufacturer(manufacturerId, manufacturer);

        if (manufacturer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (manufacturer.getId() != null)
                return Response.status(Response.Status.OK).entity(manufacturer).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{manufacturerId}")
    public Response deleteCustomer(@PathParam("manufacturerId") String manufacturerId) {

        boolean deleted = manufacturersBean.deleteManufacturer(manufacturerId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
