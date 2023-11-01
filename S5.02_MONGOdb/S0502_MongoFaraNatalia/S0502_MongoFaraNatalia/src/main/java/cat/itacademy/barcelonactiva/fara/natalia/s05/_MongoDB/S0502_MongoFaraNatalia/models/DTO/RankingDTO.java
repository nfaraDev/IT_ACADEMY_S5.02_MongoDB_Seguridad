package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.text.DecimalFormat;
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO implements Serializable {

    private String id;
    private String namePlayer;
    private int gamesWon;
    private int gamesTotal;
    private double successRate;


    public double getSuccessRate() {
        return successRate;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##0.00");
        return "id: "  + id + " "  + namePlayer +" , Won= "  + gamesWon + " , Total="  + gamesTotal +
                " , successRate="  + df.format(successRate * 100) +  "%\n";

    }
}
