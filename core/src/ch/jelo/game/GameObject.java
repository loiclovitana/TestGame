package ch.jelo.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject implements Comparable<GameObject> {
    public final Vector2 position;
    public int z;
    public final Rectangle bounds;


    public GameObject( float x, float y, float width, float height) {
        this(x, y, width, height, 0);
    }

    public GameObject(float x, float y, float width, float height, int depth) {

        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
        this.z = depth;
    }

    public void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public int compareTo(GameObject o) {
        return o.z != this.z ? Integer.compare(this.z,o.z) : Float.compare(this.position.y, o.position.y);
    }

    public abstract void update(float delta, GameState gameState);

    public abstract void draw(SpriteBatch batch);

}
