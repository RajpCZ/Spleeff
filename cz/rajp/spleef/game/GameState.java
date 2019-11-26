package cz.rajp.spleef.game;

public enum GameState {

    WAITING(true), IN_GAME(true), RESET(false);

    private boolean canJoin;

    private static GameState currentState;

    GameState(boolean canJoinn) {
        this.canJoin = canJoinn;
    }

    public boolean canJoin() {
        return canJoin;
    }

    public static GameState getState() {
        return currentState;
    }

    public static void setState(GameState state) {
        currentState = state;
    }

    public static boolean isState(GameState s) {
        return currentState == s;
    }

}
