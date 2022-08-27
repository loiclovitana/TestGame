package ch.jelo.game;

public class Player extends ControlledGameObject {

    private final static float PLAYER_WIDTH=64;
    private final static float PLAYER_HEIGHT =64;

    private float speed = 200f;

    public Player(float x, float y) {
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public float getSpeed() {
        return speed;
    }
}
