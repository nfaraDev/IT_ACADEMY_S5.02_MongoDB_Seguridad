package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(Class<?> type, Long id) {
        super(type.getSimpleName() + " with id " + id + " not found");
    }

    public ElementNotFoundException(Class<?> type, String key) {
        super(type.getSimpleName() + " with key " + key + " not found");
    }

}
