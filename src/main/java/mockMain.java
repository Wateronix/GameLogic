public class mockMain {
    public static void main(String[] args) {
        Meistertechnik m = new Meistertechnik(FighterClass.BARBARIAN);
        m.setActions(ActionSchema.builder().parries(2).attacks(1,0,1).breakers(2,0,2).counters(0,1,0).build());
        System.out.println(m.describe());
    }
}