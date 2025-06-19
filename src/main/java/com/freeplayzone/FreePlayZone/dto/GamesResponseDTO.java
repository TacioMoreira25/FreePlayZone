package com.freeplayzone.FreePlayZone.dto;

public record GamesResponseDTO(String name,
                               String imageUrl,
                               String mainGenre,
                               String ReleaseDate,
                               boolean isFree) {
}
