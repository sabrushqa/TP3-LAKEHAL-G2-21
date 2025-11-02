package ma.emsi.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiquResource {


    @GET
    @Path("lieu/{ville_ou_pays}")
    public Response villeOuPays(@PathParam("ville_ou_pays") String lieu) {
        return Response.ok(lieu).build();
    }
}
