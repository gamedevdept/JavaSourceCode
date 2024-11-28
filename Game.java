class Game {


    public int play(String key, String opponentTry) {

        int strike = 0;
        int ball = 0;

        char[] keyList = {key.charAt(0), key.charAt(1), key.charAt(2)};
        char[] opponentList = {opponentTry.charAt(0), opponentTry.charAt(1), opponentTry.charAt(2)};

        for (int i = 0; i <= 2; i++) {
            if (keyList[i] == opponentList[i]) {
                strike++;
            }
            else{

                for (int j = 0; j <= 2; j++) {
                    if (opponentList[i] == keyList[j]) {
                        ball++;
                    }
                }

            }
        }
        return strike * 10 + ball;
    }
}
