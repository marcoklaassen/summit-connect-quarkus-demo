package click.klaassen.person;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/persons")
@ApplicationScoped
public class PersonResource {

    @GET
    public Uni<List<Person>> get() {
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Person> findById(Long id) {
        return Person.findById(id);
    }

    @POST
    public Uni<Response> create(Person person) {
        return Panache.<Person>withTransaction(person::persist)
                .onItem()
                .transform(inserted ->
                        Response.created(
                                URI.create("/persons/" + inserted.id)
                        ).build());
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@RestPath Long id, Person person) {
        if (person == null || person.firstname == null || person.lastname == null) {
            throw new WebApplicationException("Person should have a firstname and a lastname", 422);
        }

        return Panache.withTransaction(() -> Person.<Person>findById(id)
                        .onItem().ifNotNull()
                        .invoke(entity -> entity.firstname = person.firstname)
                        .invoke(entity -> entity.lastname = person.lastname))
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(Response.Status.NOT_FOUND)::build);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@RestPath Long id) {
        return Panache.withTransaction(() -> Person.deleteById(id))
                .map(deleted ->
                        deleted ? Response.ok().status(Response.Status.NO_CONTENT).build()
                                : Response.ok().status(Response.Status.NOT_FOUND).build());
    }
}
