package com.freeplayzone.FreePlayZone.repository;

import com.freeplayzone.FreePlayZone.domain.gameLibrary.GameLibrary;
import com.freeplayzone.FreePlayZone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameLibraryRepository extends JpaRepository<GameLibrary, Long>
{
    Optional<GameLibrary> findByUserAndName(User user, String name);

    List<GameLibrary> findAllByUser(User user);

    void deleteByUserAndName(User user, String name);
}
