package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.exceptions;

public class ElementAlreadyExistsException extends RuntimeException {
    public ElementAlreadyExistsException(Class type, String name) {
        super("Element of " + type.getSimpleName() + " with name " + name + " already exists");
    }

}
