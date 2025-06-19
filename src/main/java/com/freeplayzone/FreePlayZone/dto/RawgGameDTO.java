package com.freeplayzone.FreePlayZone.dto;

import java.util.List;

public record RawgGameDTO(
        String name,
        String background_image,
        String released,
        List<RawgTagDTO> tags
) {}

