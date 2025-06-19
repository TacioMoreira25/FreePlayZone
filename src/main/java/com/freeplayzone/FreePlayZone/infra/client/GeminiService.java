package com.freeplayzone.FreePlayZone.infra.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class GeminiService
{
    @Value("${gemini.api.key}")
    private String apiKey;

    private Client client;

    @PostConstruct
    public void initClient()
    {
        client = new Client();
    }
    public List<String> extractGenresFromDescription(String description)
    {
        try {
            String prompt = String.format(
                    "Dado a descrição: \"%s\", retorne apenas uma lista JSON com os gêneros do jogo," +
                            " sem texto adicional. Exemplo: [\"action\", \"rpg\"]",
                    description);

            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash",
                    prompt, null);

            String output = response.text();
            System.out.println("Resposta Gemini API: " + output);

            output = output.strip();
            if (output.startsWith("```json"))
            {
                output = output.substring(7);
                if (output.endsWith("```")) {
                    output = output.substring(0, output.length() - 3);
                }
                output = output.trim();
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(output, List.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar Gemini API: " + e.getMessage(), e);
        }
    }


}
