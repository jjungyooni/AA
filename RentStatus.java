public enum RentStatus {
    RENTED(0), RETURNED(1);
    private int value;

    public int getValue() {
        return this.value;
    }


    RentStatus(int value) {
        this.value = value;
    }

    public static RentStatus fromInteger(int x) {
        switch(x) {
            case 0:
                return RENTED;
            case 1:
                return RETURNED;
        }
        return null;
    }
}
