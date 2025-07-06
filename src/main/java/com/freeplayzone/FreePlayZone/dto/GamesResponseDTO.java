package com.freeplayzone.FreePlayZone.dto;

public record GamesResponseDTO(String name,
                                   String imageUrl,
                               String mainGenre,
                               String releaseDate,
                               boolean isFree) {
}
