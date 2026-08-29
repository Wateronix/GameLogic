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

    private void addEvent(String type, String message){
        events.add(new Event(type, turn, message));
    }

    public void declareAction(boolean fighterID){
        getFighter(fighterID).setReady(true);

        if (fighter1.isReady() && fighter2.isReady())
            resolveActions();
    }

    public void unDeclareAction(boolean fighterID){
        getFighter(fighterID).setReady(false);
    }

    public void resetActions(boolean fighterID){
        getFighter(fighterID).setMeistertechnik(new Meistertechnik(getFighter(fighterID).getFighterClass()));
    }

    private void resolveActions(){
        addEvent("pre",
                fighter1.getName() + "has used " +
                        fighter1.getMeistertechnik().getCost() + " Soulstrain to perform the following Actions: " +
                        fighter1.getMeistertechnik().describe());
        addEvent("pre",
                fighter2.getName() + "has used " +
                        fighter2.getMeistertechnik().getCost() + " Soulstrain to perform the following Actions: " +
                        fighter2.getMeistertechnik().describe());

        fighter1.setPreviousMeistertechnik(fighter1.getMeistertechnik());
        fighter2.setPreviousMeistertechnik(fighter2.getMeistertechnik());

        ActionSchema actions1 = fighter1.getMeistertechnik().getActions().copy();
        ActionSchema actions2 = fighter2.getMeistertechnik().getActions().copy();

        Zones hits1 = new Zones(0,0,0);
        Zones hits2 = new Zones(0,0,0);

        applyCounters(actions1.counters(), actions2.attacks(), hits2, true);
        applyCounters(actions2.counters(), actions1.attacks(), hits1, false);

        applyBlocks(actions2.blocks(), actions1.breakers(), actions1.attacks(), true);
        applyBlocks(actions1.blocks(), actions2.breakers(), actions2.attacks(), false);

        applyParries(actions2.parries(), actions1.attacks(), hits2, true);
        applyParries(actions1.parries(), actions2.attacks(), hits1, false);

        int damage1 = applyHits(true, hits1);
        int damage2 = applyHits(false, hits2);

        fighter1.applySoulStrain(fighter1.getMeistertechnik().getCost());
        fighter2.applySoulStrain(fighter2.getMeistertechnik().getCost());

        addEvent("post",
                fighter1.getName() + " has used " + fighter1.getMeistertechnik().getCost() +
                        " Soulstrain and took " + damage1 + " Wounds");

        addEvent("post",
                fighter2.getName() + " has used " + fighter2.getMeistertechnik().getCost() +
                        " Soulstrain and took " + damage2 + " Wounds");

        boolean fighter1Alive = fighter1.getWounds()>0;
        boolean fighter2Alive = fighter1.getWounds()>0;

        if (fighter1Alive && fighter2Alive) {
            fighter1.setMeistertechnik(new Meistertechnik(fighter1.getFighterClass()));
            fighter1.setReady(false);
            fighter2.setMeistertechnik(new Meistertechnik(fighter2.getFighterClass()));
            fighter2.setReady(false);
            nextTurn();
            addEvent("new", "Start of Turn " + turn);
        }
        else {
            if (fighter1Alive) addEvent("end",
                    fighter2.getName() + " collapses from their wounds. " + fighter1.getName() + " is VICTORIOUS!");
            else if (fighter2Alive) addEvent("end",
                    fighter1.getName() + " collapses from their wounds. " + fighter2.getName() + " is VICTORIOUS!");
            else addEvent("end",
                        fighter1.getName() + " and " + fighter2.getName() + " collapse at the same time. The fight is a DRAW!");
        }
    }

    private int applyHits(boolean fighterID, Zones hits){
        Fighter target = getFighter(fighterID);
        Fighter source = getFighter(!fighterID);
        int baseDamage = 8;
        if (source.getFighterClass().equals(FighterClass.BARBARIAN)) baseDamage *= 2;
        for (int i = hits.high; i>0;i--){
            addEvent("hit",
                    source.getName() + " lands a high attack on " + target.getName() + " for " + baseDamage*9/8 + " damage"
            );
        }
        target.applyDamage(baseDamage*hits.high*9/8);
        for (int i = hits.straight; i>0;i--){
            addEvent("hit",
                    source.getName() + " lands a straight attack on " + target.getName() + " for " + baseDamage + " damage"
            );
        }
        target.applyDamage(baseDamage*hits.straight);
        for (int i = hits.low; i>0;i--){
            addEvent("hit",
                    source.getName() + " lands a low attack on " + target.getName() + " for " + baseDamage*7/8 + " damage"
            );
        }
        target.applyDamage(baseDamage*hits.low*7/8);

        return baseDamage*hits.high*9/8 + baseDamage*hits.straight + baseDamage*hits.low*7/8;
    }

    private void applyCounters(Zones counters, Zones attacks, Zones hits, boolean fighterID) {
        int c;

        c = Math.min(counters.high, attacks.high);
        attacks.high -= c;
        hits.high += c;
        for (int i = c; i>0;i--){
            addEvent("counter",
                    getFighter(fighterID).getName() + " counters a high attack from " + getFighter(!fighterID).getName()
            );
        }

        c = Math.min(counters.straight, attacks.straight);
        attacks.straight -= c;
        hits.straight += c;
        for (int i = c; i>0;i--){
            addEvent("counter",
                    getFighter(fighterID).getName() + " counters a straight attack from " + getFighter(!fighterID).getName()
            );
        }

        c = Math.min(counters.low, attacks.low);
        attacks.low -= c;
        hits.low += c;
        for (int i = c; i>0;i--){
            addEvent("counter",
                    getFighter(fighterID).getName() + " counters a low attack from " + getFighter(!fighterID).getName()
            );
        }
    }

    private void applyBlocks(Zones defenderBlocks, Zones attackerBreakers, Zones attackerAttacks, boolean fighterID) {
        for (int i = Math.max(defenderBlocks.high,attackerBreakers.high); i>0;i--){
            addEvent("breaker",getFighter(fighterID).getName() + " breaks the high block of " + getFighter(!fighterID));
        }
        if (defenderBlocks.high > attackerBreakers.high && attackerAttacks.high > 0) {
            addEvent("block",
                    getFighter(!fighterID).getName() + " blocks " + attackerAttacks.high + " high attack(s) from " + getFighter(fighterID).getName());
            attackerAttacks.high = 0;
        }

        for (int i = Math.max(defenderBlocks.straight,attackerBreakers.straight); i>0;i--){
            addEvent("breaker",getFighter(fighterID).getName() + " breaks the straight block of " + getFighter(!fighterID));
        }
        if (defenderBlocks.straight > attackerBreakers.straight && attackerAttacks.straight > 0) {
            addEvent("block",
                    getFighter(!fighterID).getName() + " blocks " + attackerAttacks.straight + " straight attack(s) from " + getFighter(fighterID).getName());
            attackerAttacks.straight = 0;
        }

        for (int i = Math.max(defenderBlocks.low,attackerBreakers.low); i>0;i--){
            addEvent("breaker",getFighter(fighterID).getName() + " breaks the low block of " + getFighter(!fighterID));
        }
        if (defenderBlocks.low > attackerBreakers.low && attackerAttacks.low > 0) {
            addEvent("block",
                    getFighter(!fighterID).getName() + " blocks " + attackerAttacks.low + " low attack(s) from " + getFighter(fighterID).getName());
            attackerAttacks.low = 0;
        }
    }

    private void applyParries(int parries, Zones attacks, Zones hits, boolean fighterID) {
        int c = Math.min(attacks.high, parries);
        for (int i = c; i>0;i--){
            addEvent("parry",
                    getFighter(!fighterID).getName() + " parries a high attack from " + getFighter(fighterID).getName()
            );
        }
        hits.high += attacks.high - c;
        parries -= c;

        c = Math.min(attacks.straight, parries);
        for (int i = c; i>0;i--){
            addEvent("parry",
                    getFighter(!fighterID).getName() + " parries a straight attack from " + getFighter(fighterID).getName()
            );
        }
        hits.straight += attacks.straight - c;
        parries -= c;

        c = Math.min(attacks.low, parries);
        for (int i = c; i>0;i--){
            addEvent("parry",
                    getFighter(!fighterID).getName() + " parries a low attack from " + getFighter(fighterID).getName()
            );
        }
        hits.low += attacks.low - c;
    }

    public List<Action> getActionOptions(boolean fighterID){
        return getFighter(fighterID).getMeistertechnik()
                .getOptions(getFighter(fighterID).getMaxSoulStrain() - getFighter(fighterID).getSoulStrain());
    }

    public void addFighterAction(boolean fighterID, Action action){
        getFighter(fighterID).getMeistertechnik().addAction(action);
    }

    public Meistertechnik getCurrentMeistertechnik(boolean fighterID){
        return getFighter(fighterID).getMeistertechnik();
    }

    public Meistertechnik getPreviousMeistertechnik(boolean fighterID){
        return getFighter(fighterID).getPreviousMeistertechnik();
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

    private Fighter getFighter(boolean fighterID){
        if (fighterID) return fighter1;
        else return fighter2;
    }
}
