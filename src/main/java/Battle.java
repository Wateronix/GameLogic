import java.util.ArrayList;
import java.util.List;

public class Battle {
    private int turn;
    private Fighter fighter1;
    private Fighter fighter2;
    private List<Event> events;

    public Battle() {
        this.turn = 1;
        fighter1 = new Fighter("Barbarian", 50, 25, FighterClass.BARBARIAN);
        fighter2 = new Fighter("Fencer", 50, 25 ,FighterClass.FENCER);
        events = new ArrayList<>(List.of(new Event("global",1,"Start of Turn 1")));
    }

    private void addEvent(Event event){
        events.add(event);
    }

    public void declareAction(boolean fighterID, Meistertechnik meistertechnik){
        if (fighterID) {
            fighter1.setMeistertechnik(meistertechnik);
            fighter1.setReady(true);
        }
        else {
            fighter2.setMeistertechnik(meistertechnik);
            fighter2.setReady(true);
        }
        if (fighter1.isReady() && fighter2.isReady())
            resolveActions();
    }

    public void unDeclareAction(boolean fighterID){
        if (fighterID) fighter1.setReady(false);
        else fighter2.setReady(false);
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

        if (fighter2.getFighterClass().equals(FighterClass.BARBARIAN))
            applyHits(fighter1, hits1, 16);
        else
            applyHits(fighter1, hits1, 8);
        if (fighter1.getFighterClass().equals(FighterClass.BARBARIAN))
            applyHits(fighter2, hits2, 16);
        else
            applyHits(fighter2, hits2, 8);

        fighter1.setMeistertechnik(new Meistertechnik(fighter1.getFighterClass()));
        fighter1.setReady(false);
        fighter2.setMeistertechnik(new Meistertechnik(fighter2.getFighterClass()));
        fighter2.setReady(false);

        nextTurn();
        addEvent(new Event("global", turn , "Start of Turn "+turn));
    }

    private void applyHits(Fighter target, Zones hits, int baseDamage){
        target.applyDamage(baseDamage*hits.high*9/8);
        target.applyDamage(baseDamage*hits.straight);
        target.applyDamage(baseDamage*hits.low*7/8);
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

    private void nextTurn() {
        turn++;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
