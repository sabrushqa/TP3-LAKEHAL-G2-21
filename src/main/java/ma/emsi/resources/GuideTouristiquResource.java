package ma.emsi.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.llm.LlmClient;

/**
 * Ressource REST pour le guide touristique
 * Expose les endpoints pour obtenir des recommandations touristiques
 */
@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiquResource {

    private final LlmClient llm = new LlmClient();

    /**
     * Endpoint par défaut : retourne 2 endroits à visiter
     * GET /guide/lieu/{ville_ou_pays}
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    public Response villeOuPays(@PathParam("ville_ou_pays") String lieu) {
        String reponseJson = llm.demander(lieu);
        return Response.ok(reponseJson).build();
    }

    /**
     * Endpoint avec nombre paramétrable d'endroits
     * GET /guide/lieu/{ville_ou_pays}/{nb}
     * Le nombre doit être un entier positif
     */
    @GET
    @Path("lieu/{ville_ou_pays}/{nb: \\d+}")
    public Response villeOuPaysAvecNb(@PathParam("ville_ou_pays") String lieu,
                                      @PathParam("nb") int nombre) {
        String reponseJson = llm.demanderAvecNb(lieu, nombre);
        return Response.ok(reponseJson).build();
    }
}