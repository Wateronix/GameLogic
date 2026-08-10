public class Fighter {
    private String name;
    private boolean isFirst;
    private int maxHP;
    private int hp;
    private int armor;
    private Meistertechnik meistertechnik;

    public Fighter(String name, boolean isFirst, int maxHP, int armor) {
        this.name = name;
        this.isFirst = isFirst;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.armor = armor;
        this.meistertechnik = Meistertechnik.NO_ACTION;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void looseHP(int hp){
        this.hp -= hp;
    }

    public Meistertechnik getMeistertechnik() {
        return meistertechnik;
    }

    public void setMeistertechnik(Meistertechnik meistertechnik) {
        this.meistertechnik = meistertechnik;
    }
}
