package ma.emsi.llm;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

/**
 * Client LLM utilisant exclusivement le code de haut niveau de LangChain4j
 * Plus de duplication du message système ni de mélange haut/bas niveau
 */
public class LlmClient {

    private final GuideTouristique service;

    public LlmClient() {
        // Création du service avec AiServices et GoogleAiGeminiChatModel
        // Utilisation directe dans AiServices.create() pour éviter les problèmes d'import
        this.service = AiServices.create(
                GuideTouristique.class,
                GoogleAiGeminiChatModel.builder()
                        .apiKey(System.getenv("GEMINI"))
                        .modelName("gemini-2.0-flash-exp")
                        .temperature(0.3)
                        .build()
        );
    }

    /**
     * Demande avec 2 endroits par défaut
     */
    public String demander(String lieu) {
        return service.proposerDeuxEndroits(lieu);
    }

    /**
     * Demande avec nombre d'endroits paramétrable
     * Délègue simplement au service, sans reconstruction du message
     */
    public String demanderAvecNb(String lieu, int nombre) {
        return service.proposer(lieu, nombre);
    }
}