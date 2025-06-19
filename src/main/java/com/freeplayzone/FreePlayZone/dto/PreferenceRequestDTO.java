package com.freeplayzone.FreePlayZone.dto;

import java.util.List;

public record PreferenceRequestDTO(List<String> genres,
                                   String description) {
}
