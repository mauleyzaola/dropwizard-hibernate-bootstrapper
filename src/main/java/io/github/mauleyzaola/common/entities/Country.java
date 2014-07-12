package io.github.mauleyzaola.common.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Esta es una clase que modela paises. Sirve como dependencia de la clase ADDRESS.
 * Lo interesante, es que hay una validacion de Hibernate en el campo NAME,
 * de tal suerte que se enviara un mensaje de error al intentar guardar un pais
 * sin nombre
 */
@Entity
@Table(name = "country")
public class Country extends MyBaseClass {

    @Column(name="name", unique = true)
    @Type(type="org.hibernate.type.StringClobType")
    @NotNull(message = "es un campo mandatorio")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "isocode", unique = true, length = 2)
    public String getIsoCode() {
        return isoCode.toUpperCase();
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    private String name;

    private String isoCode;
}
