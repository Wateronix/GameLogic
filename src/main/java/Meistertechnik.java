import java.util.List;

public enum Meistertechnik {
    NO_ACTION("noAction",
            "noAction",
            "No Action",
            "No Action",
            0,
            List.of()),
    BLOCK_HIGH("blockHigh",
            "basic",
            "Block High",
            "blocks",
            0,
            List.of(new Action(Type.BLOCK, Zone.HIGH))),
    ATTACK_HIGH("attackHigh",
            "basic",
            "Attack High",
            "attack",
            0,
            List.of(new Action(Type.ATTACK, Zone.HIGH))),
    COUNTER_HIGH("counterHigh",
            "basic",
            "Counter High",
            "counter",
            0,
            List.of(new Action(Type.COUNTER, Zone.HIGH))),
    BREAK_HIGH("breakHigh",
            "basic",
            "Block Breaker High",
            "break block",
            0,
            List.of(new Action(Type.BREAK, Zone.HIGH))),
    PARRY("parry",
            "basic",
            "Parry",
            "parry",
            0,
            List.of(new Action(Type.PARRY, Zone.STRAIGHT)));

    private final String id;
    private final String group;
    private final String name;
    private final String description;
    private final int cost;
    private final List<Action> actions;

    Meistertechnik(String id, String group, String name, String description, int cost, List<Action> actions) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.actions = actions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public List<Action> getActions() {
        return actions;
    }

    public String getGroup() {
        return group;
    }
}
