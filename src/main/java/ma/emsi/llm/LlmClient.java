package ma.emsi.llm;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import ma.emsi.llm.GuideTouristique;

public class LlmClient {

    private final GuideTouristique service;

    public LlmClient() {

        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI"))
                .modelName("gemini-2.5-flash")
                .temperature(0.2)
                .build();

        this.service = AiServices.create(GuideTouristique.class, model);
    }

    public String demander(String lieu) {
        return service.proposer(lieu);
    }
}
