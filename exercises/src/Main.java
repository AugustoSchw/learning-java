public class Main {

    public static void main(String[] args) {
        displayHighScorePosition("Fabio", calculateHighScorePosition(1500));
        displayHighScorePosition("Fabio", calculateHighScorePosition(1000));
        displayHighScorePosition("Fabio", calculateHighScorePosition(500));
        displayHighScorePosition("Fabio", calculateHighScorePosition(100));
        displayHighScorePosition("Fabio", calculateHighScorePosition(25));
    }

    public static void displayHighScorePosition(String playerName, int position) {
        System.out.println(playerName + " managed to get into position "+ position + " on the high score list");
    }

    public static int calculateHighScorePosition(int playerScore) {
        if (playerScore >= 1000) {
            return 1;
        } else if (playerScore >= 500) {
            return 2;
        } else if (playerScore >= 100) {
            return 3;
        }
        return 4;
    }
}