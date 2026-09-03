public enum FighterClass {
    BARBARIAN("Barbarian",
            "Born in the harsh north, they learned to fight before they learned to speak. Their attacks deal double damage.",
            55, 20, 13),
    FENCER("Fencer",
            "Trained as a noble, exiled when their house fell. Yet their blade is as sharp as ever and their technique remains flawless. One additional free action each turn.",
            45, 32, 8),
    KNIGHT("Knight",
            "In the honor of the Sun God, they took up arms and armor to fight back the darkness wherever it lies. Every second Block action is free.",
            60, 22, 8),
    BLADEDANCER("Bladedancer",
            "Raised by a dark cult to fight with unmatched speed and ferocity, they now wield their blades only for themselves. Every second Attack action is free.",
            50, 25, 8);

    private final String displayName;
    private final String description;
    private final int maxWounds;
    private final int maxSoulStrain;
    private final int baseDamage;

    FighterClass(String displayName, String description, int maxWounds, int maxSoulStrain, int baseDamage) {
        this.displayName = displayName;
        this.description = description;
        this.maxWounds = maxWounds;
        this.maxSoulStrain = maxSoulStrain;
        this.baseDamage = baseDamage;
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

    public int getMaxSoulStrain() {
        return maxSoulStrain;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}