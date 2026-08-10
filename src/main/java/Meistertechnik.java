public enum Meistertechnik {
    NO_ACTION("noAction",
            "noAction",
            "No Action",
            "No Action",
            0,
            ActionSchema.builder().build()),
    BLOCK_HIGH("blockHigh",
            "basic",
            "Block High",
            "blocks",
            0,
            ActionSchema.builder().blocks(1,0,0).build()),
    ATTACK_HIGH("attackHigh",
            "basic",
            "Attack High",
            "attack",
            0,
            ActionSchema.builder().attacks(1,0,0).build()),
    COUNTER_HIGH("counterHigh",
            "basic",
            "Counter High",
            "counter",
            0,
            ActionSchema.builder().counters(1,0,0).build()),
    BREAK_HIGH("breakHigh",
            "basic",
            "Block Breaker High",
            "break block",
            0,
            ActionSchema.builder().breakers(1,0,0).build()),
    PARRY("parry",
            "basic",
            "Parry",
            "parry",
            0,
            ActionSchema.builder().parries(1).build());

    private final String id;
    private final String group;
    private final String name;
    private final String description;
    private final int cost;
    private final ActionSchema actions;

    Meistertechnik(String id, String group, String name, String description, int cost, ActionSchema actions) {
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

    public ActionSchema getActions() {
        return actions;
    }

    public String getGroup() {
        return group;
    }
}
