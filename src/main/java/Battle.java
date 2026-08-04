import java.util.List;

public class Battle {
    private int turn;
    private String phase;
    private Fighter fighter1;
    private Fighter fighter2;
    private int distance;

    public Battle() {
        this.turn = 1;
        this.phase = "Start";
        fighter1 = new Fighter("Barbarian", true, 10, 0);
        fighter2 = new Fighter("Fencer", false, 10 ,0);
        this.distance = 10;
    }

    public List<Action> getActionList(boolean fighterID){
        return List.of(
                new Action("attack", "Normaler Angriff", "80% Treffer Chance, 1 Schaden"),
                new Action("heavyAttack", "Schwerer Angriff", "40% Treffer Chance, 2 Schaden"),
                new Action("", "Keine Aktion", "-")
        );
    }

    public void declareAction(boolean fighterID, String action){
        if (fighterID) fighter1.setAction(action);
        else fighter2.setAction(action);
        if (!fighter1.getAction().isEmpty() && !fighter2.getAction().isEmpty()) resolveActions();
    }

    public void unDeclareAction(boolean fighterID){
        if (fighterID) fighter1.setAction("");
        else fighter2.setAction("");
    }

    public void resolveActions(){
        takeAction(fighter1, fighter2);
        takeAction(fighter2, fighter1);
        fighter1.setAction("");
        fighter2.setAction("");
        nextTurn();
    }

    public void takeAction(Fighter attacker, Fighter target){
        switch (attacker.getAction()){
            case "attack":
                if (Math.random()<0.8) target.looseHP(1);
                break;
            case "heavyAttack":
                if (Math.random()<0.4) target.looseHP(2);
                break;
        }
    }

    public void nextTurn() {
        turn++;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Fighter getFighter1() {
        return fighter1;
    }

    public void setFighter1(Fighter fighter1) {
        this.fighter1 = fighter1;
    }

    public Fighter getFighter2() {
        return fighter2;
    }

    public void setFighter2(Fighter fighter2) {
        this.fighter2 = fighter2;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
