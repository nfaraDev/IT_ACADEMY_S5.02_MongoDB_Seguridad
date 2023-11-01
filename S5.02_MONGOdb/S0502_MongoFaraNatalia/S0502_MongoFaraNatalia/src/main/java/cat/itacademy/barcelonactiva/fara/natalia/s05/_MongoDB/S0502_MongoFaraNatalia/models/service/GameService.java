package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.service;

import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO.RankingDTO;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity.Game;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity.Player;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.repository.IGameRepo;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.repository.IPlayerRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GameService implements IGameService {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private IGameRepo gameRepo;
    @Autowired
    private IPlayerRepo playerRepo;

    @Override
    public Object playGame(String id) {
        Player player = playerService.getPlayerById(id);
        Game game = new Game(player.getId(), throwDices());
        gameRepo.save(game);
        return game;
    }

    @Override
    public String deleteGamesByIdPlayer(String id) {
        List<Game> games = gameRepo.findGamesByPlayerId(id);
        if (games.isEmpty() || games.size() == 0) {
            return "Player with id " + id + " has no games";
        }else {
            games.stream().forEach(game -> {
                gameRepo.delete((Game) game);
            });
            return "Games deleted";
        }
    }
    @Override
    public List<Game> getAllGamesByIdPlayer(String id) {
        return gameRepo.findGamesByPlayerId(id);
    }

    @Override
    public List<RankingDTO> getRankingGames() {
        List<RankingDTO> rankingDTOS = calcRanking();
        return rankingDTOS;
    }
    @Override
    public String getWinnerRankingPlayer() {
        List<RankingDTO> rankingDTOS = calcRanking();
        if (!rankingDTOS.isEmpty()) {
            //TODO Returns all the best ones with the same ranking.
            return rankingDTOS.stream()
                    .filter(x -> x.getSuccessRate() == rankingDTOS.get(0).getSuccessRate())
                    .toList()
                    .toString();
        }else {
            return "There aren't games";
        }
    }

    @Override
    public String getLoserRankingPlayer() {
        List<RankingDTO> rankingDTOS = calcRanking();
        if (!rankingDTOS.isEmpty()) {
            //TODO Returns all the worst with the same ranking
            return rankingDTOS.stream()
                    .filter(x -> x.getSuccessRate() == rankingDTOS.get(rankingDTOS.size() - 1).getSuccessRate())
                    .toList()
                    .toString();
        }else {
            return "There aren't games";
        }
    }

    private List<RankingDTO> calcRanking() {
        List<RankingDTO> rankingDTOS = new ArrayList<>();
        List<Player> players = playerRepo.findAll();
        List<Game> games = gameRepo.findAll();
        for (Player player : players) {
            String id = player.getId();
            long countGameWin = (int) games.stream()
                    .filter(x -> x.getPlayerId().equals(id) && x.getPoints() == 7)
                    .count();
            long totalGames = (int) games.stream()
                    .filter(x -> x.getPlayerId().equals(id))
                    .count();
            double successRate = 0.0;
            if (countGameWin > 0) {
                successRate = (double) countGameWin / totalGames;
            }
            // Si un jugador no ha jugado ningún juego, no entra en el ranking
            if (totalGames != 0) {
                RankingDTO rankingDTO = new RankingDTO(id,player.getName(),(int) countGameWin,(int)totalGames, successRate);
                rankingDTOS.add(rankingDTO);
            }
        }
        // Usando lambda para ordenar la lista en orden descendente según SuccessRate
        rankingDTOS.sort(Comparator.comparing(RankingDTO::getSuccessRate).reversed());
        return rankingDTOS;
    }

    private Integer throwDices() {
        Integer dado1 = new Random().nextInt(6) + 1;
        Integer dado2 = new Random().nextInt(6) + 1;
        return dado1 + dado2;
    }
}
