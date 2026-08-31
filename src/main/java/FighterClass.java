public enum FighterClass {
    BARBARIAN("Barbarian", "RAAAAHHHH!", 50, 25),
    FENCER("Fencer", "En Garde!", 50, 25),
    KNIGHT("Knight", "Deus Vult!", 50, 25),
    BLADEDANCER("Bladedancer", "...", 50, 25);

    private final String displayName;
    private final String description;
    private final int maxWounds;
    private final int maxSoulStrain;

    FighterClass(String displayName, String description, int maxWounds, int maxSoulStrain) {
        this.displayName = displayName;
        this.description = description;
        this.maxWounds = maxWounds;
        this.maxSoulStrain = maxSoulStrain;
    }

    public String displayName() {
        return displayName;
    }

    public String description() {
        return description;
    }

    public int maxWounds() {
        return maxWounds;
    }

    public int maxSoulStrain() {
        return maxSoulStrain;
    }
}