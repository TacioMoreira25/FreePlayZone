package com.freeplayzone.FreePlayZone.dto;

import java.util.List;

public record RawgResponseDTO(
        List<RawgGameDTO> results
) {}

