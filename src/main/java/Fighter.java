public class Fighter {
    private String name;
    private boolean isFirst;
    private int maxWounds;
    private int wounds;
    private int maxSoulStrain;
    private int soulStrain;
    private int damage;
    private int armor;
    private Meistertechnik meistertechnik;

    public Fighter(String name, boolean isFirst, int maxHP, int maxSoulStrain, int damage, int armor) {
        this.name = name;
        this.isFirst = isFirst;
        this.maxWounds = maxHP;
        this.wounds = 0;
        this.maxSoulStrain = maxSoulStrain;
        this.soulStrain = 0;
        this.damage = damage;
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

    public int getMaxWounds() {
        return maxWounds;
    }

    public void setMaxWounds(int maxWounds) {
        this.maxWounds = maxWounds;
    }

    public int getWounds() {
        return wounds;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void applyDamage(int damage){
        this.damage += damage;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getMaxSoulStrain() {
        return maxSoulStrain;
    }

    public void setMaxSoulStrain(int maxSoulStrain) {
        this.maxSoulStrain = maxSoulStrain;
    }

    public int getSoulStrain() {
        return soulStrain;
    }

    public void setSoulStrain(int soulStrain) {
        this.soulStrain = soulStrain;
    }

    public void applySoulStrain(int soulStrain){
        this.soulStrain += soulStrain;
    }

    public Meistertechnik getMeistertechnik() {
        return meistertechnik;
    }

    public void setMeistertechnik(Meistertechnik meistertechnik) {
        this.meistertechnik = meistertechnik;
    }
}
