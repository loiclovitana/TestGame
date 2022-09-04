package ch.jelo.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Set;

public class Player extends ControlledGameObject {

    private final static float PLAYER_WIDTH=64;
    private final static float PLAYER_HEIGHT =64;

    private float speed = 5f;

    public Player(float x, float y) {
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT,1);
    }

    public float getSpeed() {
        return speed;
    }


    @Override
    public void update(float delta, GameState gameState, Vector2 direction, Set<Action> actions) {
        Vector2 movement = direction.setLength(1.0f).scl(getSpeed());
        this.position.add(movement);
    }
}
