public record ActionSchema(int parries, Zones attacks, Zones counters, Zones blocks, Zones breakers) {
    public ActionSchema {
        attacks = (attacks != null) ? attacks : Zones.ZERO;
        counters = (counters != null) ? counters : Zones.ZERO;
        blocks = (blocks != null) ? blocks : Zones.ZERO;
        breakers = (breakers != null) ? breakers : Zones.ZERO;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int parries = 0;
        private Zones attacks = Zones.ZERO;
        private Zones counters = Zones.ZERO;
        private Zones blocks = Zones.ZERO;
        private Zones breakers = Zones.ZERO;

        public Builder parries(int count) { this.parries = count; return this; }
        public Builder attacks(int h, int s, int l) { this.attacks = new Zones(h, s, l); return this; }
        public Builder counters(int h, int s, int l) { this.counters = new Zones(h, s, l); return this; }
        public Builder blocks(int h, int s, int l) { this.blocks = new Zones(h, s, l); return this; }
        public Builder breakers(int h, int s, int l) { this.breakers = new Zones(h, s, l); return this; }

        public ActionSchema build() {
            return new ActionSchema(parries, attacks, counters, blocks, breakers);
        }
    }

    public ActionSchema copy(){
        return new ActionSchema(parries, attacks.copy(), counters.copy(), blocks.copy(), breakers.copy());
    }

    public ActionSchema add(ActionSchema a){
        return new ActionSchema(
                parries+a.parries,
                attacks.add(a.attacks),
                counters.add(a.counters),
                blocks.add(a.blocks),
                breakers.add(a.breakers));
    }
}