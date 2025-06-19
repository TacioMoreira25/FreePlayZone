package com.freeplayzone.FreePlayZone.infra.client;

import com.freeplayzone.FreePlayZone.dto.GamesResponseDTO;
import com.freeplayzone.FreePlayZone.dto.RawgResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class RawgService {

    private static final String RAWG_API = "https://api.rawg.io/api/games";
    private final String apiKey;
    private final RestTemplate restTemplate;

    public RawgService(@Value("${rawg.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
    }

    public List<GamesResponseDTO> searchGamesByGenres(List<String> genres) {
        List<GamesResponseDTO> games = new ArrayList<>();

        for (String genre : genres) {
            try {
                String url = RAWG_API + "?key=" + apiKey +
                        "&genres=" + URLEncoder.encode(genre, StandardCharsets.UTF_8) +
                        "&tags=free-to-play";

                HttpHeaders headers = new HttpHeaders();
                headers.set("User-Agent", "FreePlayZoneApp");
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<RawgResponseDTO> response = restTemplate.exchange(
                        url,
                        org.springframework.http.HttpMethod.GET,
                        entity,
                        RawgResponseDTO.class
                );
                if (response.getBody() != null && response.getBody().results() != null)
                {
                    response.getBody().results().forEach(game -> {
                        boolean isFree = game.tags() != null && game.tags().stream()
                                .map(tag -> tag.name().toLowerCase())
                                .anyMatch(name -> name.contains("free") || name.contains
                                        ("free-to-play") || name.contains("f2p"));

                        if (isFree) {
                            games.add(new GamesResponseDTO(
                                    game.name(),
                                    game.background_image(),
                                    genre,
                                    game.released(),
                                    true
                            ));
                        }
                    });
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar jogos do gênero: " + genre);
                e.printStackTrace();
            }
        }
        return games;
    }
}
