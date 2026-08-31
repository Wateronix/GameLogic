public class Fighter {
    private String name;
    private boolean isReady;
    private int maxWounds;
    private int wounds;
    private int maxSoulStrain;
    private int soulStrain;
    private FighterClass fighterClass;
    private Meistertechnik meistertechnik;
    private Meistertechnik previousMeistertechnik;

    public Fighter(FighterClass fighterClass) {
        this.name = fighterClass.name();
        this.fighterClass = fighterClass;
        this.isReady = false;
        this.maxWounds = fighterClass.maxWounds();
        this.wounds = maxWounds;
        this.maxSoulStrain = fighterClass.maxSoulStrain();
        this.soulStrain = maxSoulStrain;
        this.meistertechnik = new Meistertechnik(fighterClass);
        this.previousMeistertechnik = new Meistertechnik(fighterClass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
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
        this.soulStrain -= soulStrain;
    }

    public void applyDamage(int damage){
        this.wounds -= damage;
    }

    public Meistertechnik getMeistertechnik() {
        return meistertechnik;
    }

    public void setMeistertechnik(Meistertechnik meistertechnik) {
        this.meistertechnik = meistertechnik;
    }

    public FighterClass getFighterClass() {
        return fighterClass;
    }

    public void setFighterClass(FighterClass fighterClass) {
        this.fighterClass = fighterClass;
    }

    public Meistertechnik getPreviousMeistertechnik() {
        return previousMeistertechnik;
    }

    public void setPreviousMeistertechnik(Meistertechnik previousMeistertechnik) {
        this.previousMeistertechnik = previousMeistertechnik;
    }
}
