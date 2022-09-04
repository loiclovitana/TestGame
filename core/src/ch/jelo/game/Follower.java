package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Follower extends GameObject {

    private final static float SPEED = 70f;
    private float loveDistance = 70f;
    private static Texture penguinImage = new Texture(Gdx.files.internal("wildpenguin.png"));

    public Follower(float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    public float getLoveDistance() {
        return loveDistance;
    }

    public void update(float delta, GameState gameState) {
        Vector2 directionToPlayer = this.position.cpy().scl(-1.f).add(gameState.player.position);
        this.position.add(directionToPlayer.setLength(SPEED*delta));


    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(penguinImage, this.position.x, this.position.y, this.bounds.width, this.bounds.height);

    }

}
