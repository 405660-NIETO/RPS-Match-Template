package ar.edu.utn.frc.tup.lciii.videos.services.impl;

import ar.edu.utn.frc.tup.lciii.videos.entities.GameEntity;
import ar.edu.utn.frc.tup.lciii.videos.models.Game;
import ar.edu.utn.frc.tup.lciii.videos.repositories.jpa.GameJpaRepository;
import ar.edu.utn.frc.tup.lciii.videos.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Game getGameById(Long id) {
        GameEntity gameEntity = gameJpaRepository.getReferenceById(id);
        return modelMapper.map(gameEntity,Game.class);
    }
}
