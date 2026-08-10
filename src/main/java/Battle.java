import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Battle {
    private int turn;
    private String phase;
    private Fighter fighter1;
    private Fighter fighter2;
    private int distance;
    private List<Event> events;

    public Battle() {
        this.turn = 1;
        this.phase = "Start";
        fighter1 = new Fighter("Barbarian", true, 10, 0);
        fighter2 = new Fighter("Fencer", false, 10 ,0);
        this.distance = 10;
        events = new ArrayList<>(List.of(new Event("global",1,"Start of Turn 1")));
    }

    private void addEvent(Event event){
        events.add(event);
    }

    public void declareAction(boolean fighterID, String meistertechnikId){
        Optional<Meistertechnik> action = Arrays.stream(Meistertechnik.values()).filter(meistertechnik -> meistertechnik.getId().equals(meistertechnikId)).findFirst();
        action.ifPresent(meistertechnik ->
                {
                    if (fighterID) fighter1.setMeistertechnik(meistertechnik);
                    else fighter2.setMeistertechnik(meistertechnik);
                    if (!(fighter1.getMeistertechnik() == Meistertechnik.NO_ACTION) && !(fighter2.getMeistertechnik() == Meistertechnik.NO_ACTION))
                        resolveActions();
                }
        );
    }

    private void resolveActions(){


        //TODO
        fighter1.setMeistertechnik(Meistertechnik.NO_ACTION);
        fighter2.setMeistertechnik(Meistertechnik.NO_ACTION);
        nextTurn();
        addEvent(new Event("global", turn , "Beginning of Turn "+turn));
    }

    public List<Meistertechnik> getMeistertechnikList(String group){
        return Arrays.stream(Meistertechnik.values()).filter(meistertechnik -> meistertechnik.getGroup().equals(group)).toList();
    }

    private void nextTurn() {
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
