package click.klaassen.person;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Cacheable
public class Person extends PanacheEntity {
    @Column public String firstname;
    @Column public String lastname;
}
