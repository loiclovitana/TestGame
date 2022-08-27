package ch.jelo.game;

import com.badlogic.gdx.math.Vector2;

public class ControlledGameObject extends GameObject {
    public final Vector2 velocity;

    public ControlledGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
    }
}