package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;

public abstract class ControlledGameObject extends GameObject {
    //public final Vector2 velocity;

    // public final Controller controller;

    // public final Map<Key,Action>

    public ControlledGameObject(float x, float y, float width, float height, int depth) {
        super(x, y, width, height, 1);
        //velocity = new Vector2();
    }

    @Override
    public final void update(float delta, GameState gameState) {
        // Update of Player
        Vector2 movementDirection = new Vector2(0f,0f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            movementDirection.add(-1f, 0f);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            movementDirection.add(1f, 0f);

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            movementDirection.add(0f,1f);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            movementDirection.add(0f, -1f);

        update(delta, gameState, movementDirection, new HashSet<Action>());
    }

    public abstract void update(float delta, GameState gameState, Vector2 direction, Set<Action> actions);
}