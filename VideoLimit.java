public enum VideoLimit {
    VHS(5), CD(3), DVD(2);
    private final int value;

        VideoLimit(int value){
            this.value = value;
        }


        public int getValue() {
            return value;
        }

        public static VideoLimit fromInteger(int v) {

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


