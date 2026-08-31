import java.util.Arrays;
import java.util.List;

public class Lobby {
    public Lobby() {
    }

    public Battle newBattle(String fighter1, String fighter2){
        FighterClass f1 = Arrays.stream(FighterClass.values())
                .toList()
                .stream()
                .filter(fighterClass -> fighterClass.displayName().equals(fighter1))
                .findFirst()
                .orElse(FighterClass.BARBARIAN);
        FighterClass f2 = Arrays.stream(FighterClass.values())
                .toList()
                .stream()
                .filter(fighterClass -> fighterClass.displayName().equals(fighter2))
                .findFirst()
                .orElse(FighterClass.BARBARIAN);

        return new Battle(f1,f2);
    }


    public List<FighterClass> getFighterOptions(){
        return Arrays.stream(FighterClass.values()).toList();
    }
}
