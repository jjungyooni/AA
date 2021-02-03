public enum VideoPanalty {
    VHS(1), CD(2), DVD(3);

    private final int value;

        VideoPanalty(int value){
            this.value = value;

        }


        public int getValue() {
            return value;
        }

        public static VideoPanalty fromInteger(int v) {
            switch(v) {
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


