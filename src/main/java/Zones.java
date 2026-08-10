public record Zones(int high, int straight, int low) {
    public static final Zones ZERO = new Zones(0, 0, 0);

    // Static factories so you only specify what you need
    public static Zones high(int val) { return new Zones(val, 0, 0); }
    public static Zones straight(int val) { return new Zones(0, val, 0); }
    public static Zones low(int val) { return new Zones(0, 0, val); }
}