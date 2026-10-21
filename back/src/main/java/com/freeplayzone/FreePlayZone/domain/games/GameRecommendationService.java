package com.freeplayzone.FreePlayZone.domain.games;

import com.freeplayzone.FreePlayZone.dto.GamesResponseDTO;
import com.freeplayzone.FreePlayZone.dto.PreferenceRequestDTO;
import com.freeplayzone.FreePlayZone.infra.client.GeminiService;
import com.freeplayzone.FreePlayZone.infra.client.RawgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GameRecommendationService {

    private final GeminiService geminiService;
    private final RawgService rawgService;

    public List<GamesResponseDTO> recommendGames(PreferenceRequestDTO dto)
    {
        Set<String> genres = new HashSet<>();

        if (dto.genres() != null && !dto.genres().isEmpty())
        {
            genres.addAll(dto.genres());
        }
        if (dto.description() != null && !dto.description().isBlank())
        {
            List<String> extracted = geminiService.extractGenresFromDescription(dto.description());
            genres.addAll(extracted);
        }
        return rawgService.searchGamesByGenres(new ArrayList<>(genres));
    }
}
