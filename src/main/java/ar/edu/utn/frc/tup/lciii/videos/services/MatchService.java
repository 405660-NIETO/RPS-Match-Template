package ar.edu.utn.frc.tup.lciii.videos.services;

import ar.edu.utn.frc.tup.lciii.videos.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.videos.models.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    List<Match> getMatchesByPlayer(Long playerId);

    Match createMatch(MatchDto matchDto);
}
