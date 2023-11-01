package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.exceptions;

import cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity.Player;

import org.bson.types.ObjectId;

public class ElementsDoesntEqualsException extends RuntimeException {
    public ElementsDoesntEqualsException(Class type, ObjectId id, ObjectId idDto) {
        super("Id's in " + type.getSimpleName() + " must be equals. Entered values " + id + " - "+ idDto);
    }

    public ElementsDoesntEqualsException(Class<Player> type, String id, String id1) {
    }
}
