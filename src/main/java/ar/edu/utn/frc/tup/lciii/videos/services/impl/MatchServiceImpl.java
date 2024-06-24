package ar.edu.utn.frc.tup.lciii.videos.services.impl;

import ar.edu.utn.frc.tup.lciii.videos.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.videos.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.videos.models.Game;
import ar.edu.utn.frc.tup.lciii.videos.models.Match;
import ar.edu.utn.frc.tup.lciii.videos.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import ar.edu.utn.frc.tup.lciii.videos.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.videos.services.GameService;
import ar.edu.utn.frc.tup.lciii.videos.services.MatchService;
import ar.edu.utn.frc.tup.lciii.videos.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Override
    public List<Match> getMatchesByPlayer(Long playerId) {
        List<Match> matchList = new ArrayList<>();
        Optional<List<MatchEntity>> optionalList =  matchJpaRepository.getAllByPlayerId(playerId);

        optionalList.ifPresent(list -> list.forEach(
                matchEntity -> matchList.add(modelMapper.map(matchEntity, Match.class))
        ));

        return matchList;
    }

    @Override
    public Match createMatch(MatchDto matchDto) {
        Match match = new Match();
        //(video 29) cambiar Match a una extencion del tipo MatchRps porque ahora es abstracto
        Player player = playerService.getPlayerById(matchDto.getPlayerId());
        Game game = gameService.getGameById(matchDto.getGameId());

        //Movi de lugar el match para usar la factoria (video 30)

        match.setPlayer(player);
        match.setGame(game);
        match.setCreatedDate(LocalDateTime.now());
        match.setStatus(MatchStatus.STARTED);

        MatchEntity matchEntity = matchJpaRepository.save(modelMapper.map(match, MatchEntity.class));

        return modelMapper.map(matchEntity,Match.class);
    }
}
