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

    public int sum(){
        return high+straight+low;
    }

    public Zones add(Zones a){
        return new Zones(high+a.high,straight+a.straight, low+a.low);
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getStraight() {
        return straight;
    }

    public void setStraight(int straight) {
        this.straight = straight;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }
}