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
        ActionSchema actions1 = fighter1.getMeistertechnik().getActions().copy();
        ActionSchema actions2 = fighter2.getMeistertechnik().getActions().copy();

        Zones hits1 = new Zones(0,0,0);
        Zones hits2 = new Zones(0,0,0);

        applyCounters(actions1.counters(), actions2.attacks(), hits2);
        applyCounters(actions2.counters(), actions1.attacks(), hits1);

        applyBlocks(actions2.blocks(), actions1.breakers(), actions1.attacks());
        applyBlocks(actions1.blocks(), actions2.breakers(), actions2.attacks());

        applyParries(actions2.parries(), actions1.attacks(), hits2);
        applyParries(actions1.parries(), actions2.attacks(), hits1);

        //TODO resolve hits
        fighter1.setMeistertechnik(Meistertechnik.NO_ACTION);
        fighter2.setMeistertechnik(Meistertechnik.NO_ACTION);

        nextTurn();
        addEvent(new Event("global", turn , "Beginning of Turn "+turn));
    }

    private void applyCounters(Zones counters, Zones attacks, Zones hits) {
        int c;

        c = Math.min(counters.high, attacks.high);
        attacks.high -= c;
        hits.high += c;

        c = Math.min(counters.straight, attacks.straight);
        attacks.straight -= c;
        hits.straight += c;

        c = Math.min(counters.low, attacks.low);
        attacks.low -= c;
        hits.low += c;
    }

    private void applyBlocks(Zones defenderBlocks, Zones attackerBreakers, Zones attackerAttacks) {
        if (defenderBlocks.high > attackerBreakers.high) attackerAttacks.high = 0;
        if (defenderBlocks.straight > attackerBreakers.straight) attackerAttacks.straight = 0;
        if (defenderBlocks.low > attackerBreakers.low) attackerAttacks.low = 0;
    }

    private void applyParries(int parries, Zones attacks, Zones hits) {
        int c = Math.min(attacks.high, parries);
        hits.high += attacks.high - c;
        parries -= c;

        c = Math.min(attacks.straight, parries);
        hits.straight += attacks.straight - c;
        parries -= c;

        hits.low += attacks.low - Math.min(attacks.low, parries);
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
