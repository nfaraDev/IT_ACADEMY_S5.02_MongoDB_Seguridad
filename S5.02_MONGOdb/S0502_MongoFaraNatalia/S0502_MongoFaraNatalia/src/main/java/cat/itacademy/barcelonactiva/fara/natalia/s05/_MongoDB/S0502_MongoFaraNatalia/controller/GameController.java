package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.controller;

import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO.RankingDTO;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity.Game;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.service.GameService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    //Realizar un tiro de Dado
    @PostMapping("/{id}/games")
    public ResponseEntity<String> playGame(@PathVariable String id) {

            return ResponseEntity.ok().body(gameService.playGame(id).toString());

    }

    //Listado de Jugadas por un jugador
    @GetMapping("/{id}/games")
    public ResponseEntity<String> getAllGames(@PathVariable String id) {
            List<Game> games = gameService.getAllGamesByIdPlayer(id);
            return ResponseEntity.ok().body(games.toString());

    }

    //Eliminar tirada del Jugador
    @DeleteMapping("/{id}/games")
    public ResponseEntity<String> deleteGames(@PathVariable String id) {
         return ResponseEntity.ok().body(gameService.deleteGamesByIdPlayer(id));

    }

    // Ranking Medio de Todos los Jugadores
    @GetMapping("/ranking")
    public ResponseEntity<String> getRankingGames() {
        List<RankingDTO> rankingGames = gameService.getRankingGames();
        return ResponseEntity.ok().body(rankingGames.toString());

    }

    //Jugador Peor Porcentaje de Exito
    @GetMapping("/ranking/loser")
    public ResponseEntity<String> getRankingLoser() {
        String loser = gameService.getLoserRankingPlayer();
        return ResponseEntity.ok().body(loser.toString());

    }

    //Jugador con Mejor Porcentaje de Exito
    @GetMapping("/ranking/winner")
    public ResponseEntity<String> getRankingWinner() {
        String winner = gameService.getWinnerRankingPlayer();
        return ResponseEntity.ok().body(winner);
    }

}