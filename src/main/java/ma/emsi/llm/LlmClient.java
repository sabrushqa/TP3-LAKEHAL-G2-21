package ma.emsi.llm;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;

public class LlmClient {

    private final GuideTouristique service;
    private final ChatLanguageModel model;

    public LlmClient() {
        this.model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI"))
                .modelName("gemini-2.5-flash")
                .temperature(0.2)
                .build();

        this.service = AiServices.create(GuideTouristique.class, this.model);
    }


    public String demander(String lieu) {
        return service.proposer(lieu);
    }

    //bonus (nombre d'endroits variable)
    public String demanderAvecNb(String lieu, int nb) {
        String systemMessage = """
            Tu es un guide touristique francophone.
            Donne exactement %d principaux endroits à visiter pour le lieu demandé,
            ainsi que le prix moyen d'un repas dans la devise du pays.
            Réponds STRICTEMENT au format JSON suivant (sans Markdown) :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["endroit 1", "..."],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            N'utilise pas Markdown.
            """.formatted(nb);

        String prompt = systemMessage + "\nLieu : " + lieu;

        return model.generate(prompt);
    }
}
