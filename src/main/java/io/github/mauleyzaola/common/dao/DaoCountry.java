package io.github.mauleyzaola.common.dao;

import io.dropwizard.hibernate.AbstractDAO;
import io.github.mauleyzaola.common.entities.Country;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.UUID;

public class DaoCountry extends AbstractDAO<Country> {
    public DaoCountry(SessionFactory factory) { super(factory); }

    public Country findById(UUID id){
        return (Country)currentSession().get(Country.class, id);
    }

    public Country create(Country country){
        country.generateUUId();
        country.setDateCreated(new Date());
        return persist(country);
    }

    public Country update(Country country){
        Country oldItem = findById(country.getId());
        oldItem.setName(country.getName());
        oldItem.setIsoCode(country.getIsoCode());
        oldItem.setLastModified(new Date());
        return persist(oldItem);
    }

    public Country remove(UUID id){
        Country oldItem = findById(id);
        currentSession().delete(oldItem);
        return oldItem;
    }

}
