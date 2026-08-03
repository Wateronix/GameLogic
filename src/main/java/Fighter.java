public class Fighter {
    private boolean isFirst;
    private int maxHP;
    private int hp;
    private int armor;
    private String action;

    public Fighter(boolean isFirst, int maxHP, int armor) {
        this.isFirst = isFirst;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.armor = armor;
        this.action = "";
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
