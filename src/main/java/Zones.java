public class Zones {
    int high;
    int straight;
    int low;

    public Zones(int high, int straight, int low) {
        this.high = high;
        this.straight = straight;
        this.low = low;
    }

    public static final Zones ZERO = new Zones(0, 0, 0);

    public Zones copy(){
        return new Zones(high,straight,low);
    }
}