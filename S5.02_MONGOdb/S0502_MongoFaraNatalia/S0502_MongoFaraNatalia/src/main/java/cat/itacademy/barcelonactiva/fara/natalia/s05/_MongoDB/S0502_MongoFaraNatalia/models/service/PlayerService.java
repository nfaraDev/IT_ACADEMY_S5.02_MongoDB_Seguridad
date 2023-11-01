package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.service;

import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity.Player;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.exceptions.ElementAlreadyExistsException;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.exceptions.ElementNotFoundException;
import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.repository.IPlayerRepo;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService implements IPlayerService {

    @Autowired
    ModelMapper modelMapper;
    private final IPlayerRepo playerRepo;


    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        if (findByName(playerDTO))
            throw new ElementAlreadyExistsException(Player.class, playerDTO.getName());
        else {
            Player player = playerRepo.save(convertDtoToEntity(playerDTO));
            return convertEntityToDto(player);
        }
    }

    @Override
    public Object updatePlayer(String id, PlayerDTO playerDTO) {

        Optional<Player> playerOptional = playerRepo.findById(playerDTO.getId());
        if (playerOptional.isEmpty())
            throw new ElementNotFoundException(Player.class, id);
        else {
            if (findByName(playerDTO))
                throw new ElementAlreadyExistsException(Player.class, playerDTO.getName());
            else {
                Player player = playerOptional.get();
                player.setName(playerDTO.getName());
                return playerRepo.save(player);
            }

        }
    }

    @Override
    public  Player getPlayerById(String id) {
        Optional<Player> optionalPlayer = playerRepo.findById(id);
        if (optionalPlayer.isEmpty()) throw new ElementNotFoundException(Player.class, id);
        Player player = optionalPlayer.get();
        return player;

    }

    private boolean findByName(PlayerDTO playerDTO) {
        Optional<Player> playerOptional = playerRepo.findPlayerByName(playerDTO.getName());
        if (playerOptional.isPresent())
            return true;
        else
            return false;
    }

    private Player convertDtoToEntity(PlayerDTO playerDTO) {
        Player player = modelMapper.map(playerDTO, Player.class);
        if (player.getName() == null || player.getName() == "") player.setName("ANONIMO");
        if (player.getDate() == null) player.setDate(new Date());
        return player;
    }

    private PlayerDTO convertEntityToDto(Player player) {
        return new PlayerDTO(player.getId(),player.getName());
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepo.findAll();
        return players.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

}
