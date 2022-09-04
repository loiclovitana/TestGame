package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Set;

public class Player extends ControlledGameObject {

    private static Texture playerImage = new Texture(Gdx.files.internal("penguin.png"));

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

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(playerImage, this.position.x, this.position.y, this.bounds.width, this.bounds.height);

    }
}
