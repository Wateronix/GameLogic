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

        List<Action> list = new ArrayList<>(List.of(
                new Action("aH", "Attack High", attackCost, ActionSchema.builder().attacks(1, 0, 0).build()),
                new Action("aS", "Attack Straight", attackCost, ActionSchema.builder().attacks(0, 1, 0).build()),
                new Action("aL", "Attack Low", attackCost, ActionSchema.builder().attacks(0, 0, 1).build()),
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
