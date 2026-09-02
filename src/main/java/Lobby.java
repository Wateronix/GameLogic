import java.util.Arrays;
import java.util.List;

public class Lobby {
    private FighterClass player1Selection;
    private FighterClass player2Selection;
    private boolean player1Ready;
    private boolean player2Ready;
    private Battle battle;

    public Lobby() {
        player1Selection = FighterClass.BARBARIAN;
        player2Selection = FighterClass.BARBARIAN;
        player1Ready = false;
        player2Ready = false;
    }

    public List<FighterClass> getFighterOptions(){
        return Arrays.stream(FighterClass.values()).toList();
    }

    public void selectFighter(String fighterName, boolean playerID){
        Arrays.stream(FighterClass.values())
                .toList()
                .stream()
                .filter(fighterClass -> fighterClass.getDisplayName().equals(fighterName))
                .findFirst().ifPresent(fighterClass -> {
                    if (playerID) player1Selection = fighterClass;
                    else player2Selection = fighterClass;
                });
    }

    public void ready(boolean playerID){
        if (playerID)player1Ready = true;
        else player2Ready = true;

        if (player1Ready && player2Ready)
            battle = new Battle(player1Selection,player2Selection);
    }

    public FighterClass getPlayer1Selection() {
        return player1Selection;
    }

    public void setPlayer1Selection(FighterClass player1Selection) {
        this.player1Selection = player1Selection;
    }

    public FighterClass getPlayer2Selection() {
        return player2Selection;
    }

    public void setPlayer2Selection(FighterClass player2Selection) {
        this.player2Selection = player2Selection;
    }

    public boolean isPlayer1Ready() {
        return player1Ready;
    }

    public void setPlayer1Ready(boolean player1Ready) {
        this.player1Ready = player1Ready;
    }

    public boolean isPlayer2Ready() {
        return player2Ready;
    }

    public void setPlayer2Ready(boolean player2Ready) {
        this.player2Ready = player2Ready;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
