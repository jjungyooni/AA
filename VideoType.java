public enum VideoType {
    VHS(1),CD(2),DVD(3);

    private final int value;

    VideoType(int value){
        this.value = value;


    }

    public int getValue() {
        return value;
    }

    public static VideoType fromInteger(int x) {
        switch(x) {
            case 1:
                return VHS;
            case 2:
                return CD;
            case 3:
                return DVD;
        }
            return null;
    }
}


