package com.freeplayzone.FreePlayZone.controller;

import com.freeplayzone.FreePlayZone.domain.games.GameRecommendationService;
import com.freeplayzone.FreePlayZone.dto.GamesResponseDTO;
import com.freeplayzone.FreePlayZone.dto.PreferenceRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/preferences")
@RequiredArgsConstructor
public class GameRecommendationController
{
    private final GameRecommendationService gameRecommendationService;

    @PostMapping("Games")
    public ResponseEntity<List<GamesResponseDTO>> recommendGames
            (@RequestBody PreferenceRequestDTO body)
    {
        List<GamesResponseDTO> recommendations = gameRecommendationService.recommendGames(body);
        return ResponseEntity.ok(recommendations);
    }
}
