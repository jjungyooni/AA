
public enum PriceType {
	REGULAR(1), NEW_RELEASE(2);
    private int value;

    public int getValue() {
        return this.value;
    }


    PriceType(int value) {
        this.value = value;
    }

    public static PriceType fromInteger(int x) {
        switch(x) {
            case 1:
                return REGULAR;
            case 2:
                return NEW_RELEASE;
        }
        return null;
    }
}
