package ma.emsi.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.llm.LlmClient;

@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiquResource {

    private final LlmClient llm = new LlmClient();

    @GET
    @Path("lieu/{ville_ou_pays}")
    public Response villeOuPays(@PathParam("ville_ou_pays") String lieu) {
        String reponseJson = llm.demander(lieu);
        return Response.ok(reponseJson).build();
    }
}
