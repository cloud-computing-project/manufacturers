package si.fri.rso.samples.manufacturers.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.samples.manufacturers.models.Manufacturer;
import si.fri.rso.samples.manufacturers.services.ManufacturersBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@RequestScoped
@Path("/manufacturers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class ManufacturersResource {

    @Inject
    private ManufacturersBean manufacturersBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getManufacturers() {

        List<Manufacturer> manufacturers = manufacturersBean.getManufacturers();

        return Response.ok(manufacturers).build();
    }

    @GET
    @Path("/filtered")
    public Response getManufacturersFiltered() {

        List<Manufacturer> manufacturers;

        manufacturers = manufacturersBean.getManufacturersFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(manufacturers).build();
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

        if ((manufacturer.getTitle() == null || manufacturer.getTitle().isEmpty()) ) {
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
    public Response putZavarovanec(@PathParam("manufacturerId") String manufacturerId, Manufacturer manufacturer) {

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
    public Response deleteManufacturer(@PathParam("manufacturerId") String manufacturerId) {

        boolean deleted = manufacturersBean.deleteManufacturer(manufacturerId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
