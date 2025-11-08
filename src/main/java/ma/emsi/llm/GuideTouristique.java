package ma.emsi.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {

    /**
     * Méthode principale avec nombre variable d'endroits
     * Utilise des templates avec variables pour éviter la duplication
     */
    @SystemMessage("""
    Tu es un guide touristique francophone.
    Donne exactement {{nombre}} principaux endroits à visiter pour le lieu demandé,
    ainsi que le prix moyen d'un repas dans la devise du pays.
    Réponds STRICTEMENT au format JSON suivant (sans Markdown ni balises ```json) :
    {
      "ville_ou_pays": "nom de la ville ou du pays",
      "endroits_a_visiter": ["endroit 1", "endroit 2", ...],
      "prix_moyen_repas": "<prix> <devise du pays>"
    }
    """)
    @UserMessage("Propose-moi des endroits à visiter à {{lieu}}")
    String proposer(@V("lieu") String lieu, @V("nombre") int nombre);

    /**
     * Méthode simplifiée avec 2 endroits par défaut
     * Évite la duplication du message système
     */
    default String proposerDeuxEndroits(String lieu) {
        return proposer(lieu, 2);
    }
}