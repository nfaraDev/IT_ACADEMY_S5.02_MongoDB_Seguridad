package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.service;


import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity.Player;

public interface IPlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    Object updatePlayer(String id, PlayerDTO playerDTO);
    Player getPlayerById(String id);

}
