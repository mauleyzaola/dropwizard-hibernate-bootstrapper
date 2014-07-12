package io.github.mauleyzaola.common.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.mauleyzaola.common.dao.DaoAddress;
import io.github.mauleyzaola.common.entities.Address;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path(value="/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceAddress {
    private final DaoAddress daoAddress;

    public ServiceAddress(DaoAddress daoAddress){
        this.daoAddress =daoAddress;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Address get(@PathParam("id") UUID id)  {
        return daoAddress.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Address remove(@PathParam("id") UUID id)  {
        return daoAddress.remove(id);
    }


    @PUT
    @UnitOfWork
    public Address create(Address address){
        return daoAddress.create(address);
    }

    @POST
    @Path("/{id}")
    @UnitOfWork
    public Address update(@PathParam("id") UUID id, Address address){
        address.setId(id);
        return daoAddress.update(address);
    }
}
