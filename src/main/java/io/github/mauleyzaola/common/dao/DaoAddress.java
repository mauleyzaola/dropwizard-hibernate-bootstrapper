package io.github.mauleyzaola.common.dao;

import io.dropwizard.hibernate.AbstractDAO;
import io.github.mauleyzaola.common.entities.Address;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.UUID;

public class DaoAddress extends AbstractDAO<Address> {
    public DaoAddress(SessionFactory factory) { super(factory); }

    public Address findById(UUID id){
        return (Address)currentSession().get(Address.class, id);
    }

    public Address create(Address address){
        address.generateUUId();
        address.setDateCreated(new Date());
        return persist(address);
    }

    public Address update(Address address){
        Address oldItem = findById(address.getId());
        oldItem.setAddressLine1(address.getAddressLine1());
        oldItem.setAddressLine2(address.getAddressLine2());
        oldItem.setCity(address.getCity());
        oldItem.setCountry(address.getCountry());
        oldItem.setIntNumber(address.getIntNumber());
        oldItem.setState(address.getState());
        oldItem.setStreet(address.getStreet());
        oldItem.setStreetNumber(address.getStreetNumber());
        oldItem.setZipCode(address.getZipCode());
        oldItem.setLastModified(new Date());
        return persist(oldItem);
    }

    public Address remove(UUID id){
        Address oldItem = findById(id);
        currentSession().delete(oldItem);
        return oldItem;
    }

}
