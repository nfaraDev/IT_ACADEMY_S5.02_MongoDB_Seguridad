package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.service;


import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO.RankingDTO;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity.Game;

import java.util.List;

public interface IGameService {

    Object playGame(String id);
    String deleteGamesByIdPlayer(String id);
    List<Game> getAllGamesByIdPlayer(String id);
    List<RankingDTO> getRankingGames();
    String getWinnerRankingPlayer();
    String getLoserRankingPlayer();


}
