import java.util.ArrayList;
import java.util.List;

public class Meistertechnik {
    private FighterClass fighterClass;
    private int cost;
    private ActionSchema actions;


    Meistertechnik(FighterClass fighterClass) {
        this.fighterClass = fighterClass;
        this.cost = 0;
        this.actions = ActionSchema.builder().build();
    }

    public List<Action> getOptions(int mana){
        int attacks = actions.attacks().sum();
        int parries = actions.parries();
        int blocks = actions.blocks().sum();
        int breakers = actions.breakers().sum();
        int counters = actions.counters().sum();
        int nextCost = attacks+parries+blocks+breakers+counters;

        if (fighterClass.equals(FighterClass.BLADEDANCER)) {
            nextCost -= attacks/2;
        }
        if (fighterClass.equals(FighterClass.KNIGHT)) {
            nextCost -= blocks/2;
        }
        if (fighterClass.equals(FighterClass.FENCER)) {
            nextCost = Math.max(0, nextCost-1);
        }
        int attackCost = nextCost;
        int blockCost = nextCost;
        int parryCost = nextCost;
        int breakCost = nextCost;
        int counterCost = nextCost;
        if (fighterClass.equals(FighterClass.BLADEDANCER) && (attacks % 2 != 0)) {
            attackCost = 0;
        }
        if (fighterClass.equals(FighterClass.KNIGHT) && (blocks % 2 != 0)) {
            blockCost = 0;
        }
        int maxCost = mana - cost;

        String highD = " (9 Dmg)";
        String strD = " (8 Dmg)";
        String lowD = " (7 Dmg)";
        if (fighterClass.equals(FighterClass.BARBARIAN)){
            highD = " (18 Dmg)";
            strD = " (16 Dmg)";
            lowD = " (14 Dmg)";
        }

        List<Action> list = new ArrayList<>(List.of(
                new Action("aH", "Attack High"+highD, attackCost, ActionSchema.builder().attacks(1, 0, 0).build()),
                new Action("aS", "Attack Straight"+strD, attackCost, ActionSchema.builder().attacks(0, 1, 0).build()),
                new Action("aL", "Attack Low"+lowD, attackCost, ActionSchema.builder().attacks(0, 0, 1).build()),
                new Action("p", "Parry", parryCost, ActionSchema.builder().parries(1).build()),
                new Action("bH", "Block High", blockCost, ActionSchema.builder().blocks(1, 0, 0).build()),
                new Action("bS", "Block Straight", blockCost, ActionSchema.builder().blocks(0, 1, 0).build()),
                new Action("bL", "Block Low", blockCost, ActionSchema.builder().blocks(0, 0, 1).build()),
                new Action("bbH", "Block Breaker High", breakCost, ActionSchema.builder().breakers(1, 0, 0).build()),
                new Action("bbS", "Block Breaker Straight", breakCost, ActionSchema.builder().breakers(0, 1, 0).build()),
                new Action("bbL", "Block Breaker Low", breakCost, ActionSchema.builder().breakers(0, 0, 1).build()),
                new Action("cH", "Counter High", counterCost, ActionSchema.builder().counters(1, 0, 0).build()),
                new Action("cS", "Counter Straight", counterCost, ActionSchema.builder().counters(0, 1, 0).build()),
                new Action("cL", "Counter Low", counterCost, ActionSchema.builder().counters(0, 0, 1).build())
        )).stream().filter(action -> action.cost() <= maxCost).toList();
        return list;
    }

    public void addAction(Action action){
        actions = actions.add(action.actions());
        cost += action.cost();
    }

    public String describe(){
        String out = "";
        out += actionNumber("Block Breaker High", actions.breakers().high);
        out += actionNumber("Attack Straight", actions.attacks().high);
        out += actionNumber("Block Breaker Straight", actions.breakers().straight);
        out += actionNumber("Attack Straight", actions.attacks().straight);
        out += actionNumber("Block Breaker Low", actions.breakers().low);
        out += actionNumber("Attack Low", actions.attacks().low);

        out += actionNumber("Counter High", actions.counters().high);
        out += actionNumber("Counter Straight", actions.counters().straight);
        out += actionNumber("Counter Low", actions.counters().low);

        out += actionNumber("Block High", actions.blocks().high);
        out += actionNumber("Block Straight", actions.blocks().straight);
        out += actionNumber("Block Low", actions.blocks().low);

        out += actionNumber("Parry", actions.parries());
        out = out.substring(0, Math.max(0, out.length() - 2));
        return out;
    }

    private String actionNumber(String action, int number){
        if (number == 0) return "";
        else if (number == 1) return action + ", ";
        else return action + " x" + number + ", ";
    }


    public FighterClass getFighterClass() {
        return fighterClass;
    }

    public void setFighterClass(FighterClass fighterClass) {
        this.fighterClass = fighterClass;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ActionSchema getActions() {
        return actions;
    }

    public void setActions(ActionSchema actions) {
        this.actions = actions;
    }
}
