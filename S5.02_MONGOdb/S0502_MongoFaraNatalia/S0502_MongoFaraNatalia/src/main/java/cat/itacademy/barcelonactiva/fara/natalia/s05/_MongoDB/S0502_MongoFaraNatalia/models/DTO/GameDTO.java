package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO;

import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Setter
@Getter
public class GameDTO implements Serializable {
    @Id
    private String id;
    private Integer points;
    private String playerId;

    @Override
    public String toString() {
        return "id=" + id + ", points=" + points + ", player: " + playerId;
    }
}
