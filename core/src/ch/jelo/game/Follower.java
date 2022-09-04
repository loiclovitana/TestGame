package ch.jelo.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Follower extends GameObject {

    private float loveDistance = 70f;

    public Follower(float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    public float getLoveDistance() {
        return loveDistance;
    }

    public void update(float delta, GameState gameState) {
        //todo
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

}
