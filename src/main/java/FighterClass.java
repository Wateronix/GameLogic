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

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxWounds() {
        return maxWounds;
    }

    public int getBaxSoulStrain() {
        return maxSoulStrain;
    }
}