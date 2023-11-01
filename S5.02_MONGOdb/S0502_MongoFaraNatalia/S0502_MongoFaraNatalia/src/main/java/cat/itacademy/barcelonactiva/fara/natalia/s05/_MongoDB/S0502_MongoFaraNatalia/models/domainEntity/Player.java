package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.domainEntity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Document(collection = "player")
public class Player {
    @Id
    private String id;
    private String name;
    @CreatedDate
    private Date date;

    @Override
    public String toString() {
        return "Player " + id + "-" + name;
    }
}