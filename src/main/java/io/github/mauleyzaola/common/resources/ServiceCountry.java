package io.github.mauleyzaola.common.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.mauleyzaola.common.dao.DaoCountry;
import io.github.mauleyzaola.common.entities.Country;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path(value="/country")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceCountry {
    private final DaoCountry daoCountry;

    public ServiceCountry(DaoCountry daoCountry){
        this.daoCountry =daoCountry;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Country get(@PathParam("id") UUID id)  {
        return daoCountry.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Country remove(@PathParam("id") UUID id)  {
        return daoCountry.remove(id);
    }


    @PUT
    @UnitOfWork
    public Country create(Country country){
        return daoCountry.create(country);
    }

    @POST
    @Path("/{id}")
    @UnitOfWork
    public Country update(@PathParam("id") UUID id, Country country){
        country.setId(id);
        return daoCountry.update(country);
    }
}
