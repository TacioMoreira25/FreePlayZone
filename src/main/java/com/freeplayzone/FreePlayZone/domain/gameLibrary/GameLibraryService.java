package com.freeplayzone.FreePlayZone.domain.gameLibrary;

import com.freeplayzone.FreePlayZone.domain.user.User;
import com.freeplayzone.FreePlayZone.dto.GamesResponseDTO;
import com.freeplayzone.FreePlayZone.repository.GameLibraryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameLibraryService
{
    @Autowired
    private GameLibraryRepository gameLibraryRepository;

    @Transactional
    public void addJogo(User user, GamesResponseDTO jogoDTO)
    {
        if (gameLibraryRepository.findByUserAndName(user, jogoDTO.name()).isPresent()) {
            throw new RuntimeException("Esse jogo já está na sua biblioteca.");
        }

        GameLibrary jogo = new GameLibrary();
        jogo.setUser(user);
        jogo.setName(jogoDTO.name());
        jogo.setImageUrl(jogoDTO.imageUrl());
        jogo.setMainGenre(jogoDTO.mainGenre());
        jogo.setReleaseDate(jogoDTO.releaseDate());
        jogo.setFree(jogoDTO.isFree());

        gameLibraryRepository.save(jogo);
    }

    public List<GamesResponseDTO> listarBiblioteca(User user) {
        return gameLibraryRepository.findAllByUser(user)
                .stream()
                .map(jogo -> new GamesResponseDTO(
                        jogo.getName(),
                        jogo.getImageUrl(),
                        jogo.getMainGenre(),
                        jogo.getReleaseDate(),
                        jogo.isFree()
                )).toList();
    }

    @Transactional
    public void removerJogo(User user, String nome)
    {
        gameLibraryRepository.deleteByUserAndName(user, nome);
    }
}

