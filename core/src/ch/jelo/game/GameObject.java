package ch.jelo.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public abstract class GameObject {

    public static GameObjectDrawComparator GAME_OBJECT_DRAW_COMPARATOR = new GameObject.GameObjectDrawComparator();

    public final Vector2 position;
    public int z;
    public final Rectangle bounds;


    public GameObject(float x, float y, float width, float height) {
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

    private static class GameObjectDrawComparator implements Comparator<GameObject> {

        @Override
        public int compare(GameObject o1, GameObject o2) {
            return o1.z != o2.z ? Integer.compare(o1.z, o2.z) : - Float.compare(o1.position.y, o2.position.y);
        }
    }


    public abstract void update(float delta, GameState gameState);

    public abstract void draw(SpriteBatch batch);

}
