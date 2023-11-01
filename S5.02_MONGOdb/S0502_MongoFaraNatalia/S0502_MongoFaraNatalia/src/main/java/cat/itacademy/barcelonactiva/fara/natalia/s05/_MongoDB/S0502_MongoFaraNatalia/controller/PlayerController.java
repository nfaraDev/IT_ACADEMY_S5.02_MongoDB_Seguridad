package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.controller;

import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO.PlayerDTO;

import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    public final PlayerService playerService;

    // Crear un Jugador
    @PostMapping("/add")
    public ResponseEntity<Object> createPlayer(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok().body(playerService.createPlayer(playerDTO));

    }

    // Modificar el Nombre del Jugador
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePlayer(@PathVariable String id,@RequestBody PlayerDTO playerDTO) {

            return ResponseEntity.ok().body(playerService.updatePlayer(id, playerDTO));

    }

    // Listado de Todos los Jugadores (Nueva Función)
    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }
}