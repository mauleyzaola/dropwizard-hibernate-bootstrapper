package io.github.mauleyzaola.common.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.mauleyzaola.common.utils.JsonDateSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

/**
 * Esta clase en realidad no se mapea con una tabla, porque sirve como base para crear otras clases
 * que si se mapean. Las propiedades id, dateCreated y lastModified se reutilizan en el resto de las
 * clases hijas.
 */
@MappedSuperclass
public class MyBaseClass {

    @Id
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    protected UUID id;

    protected Date dateCreated;

    protected Date lastModified;

    @Column(nullable = false, columnDefinition = "timestamp with time zone")
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(nullable = true, columnDefinition = "timestamp with time zone")
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }


    public MyBaseClass() { }

    public void generateUUId(){
        this.id = UUID.randomUUID();
    }
}