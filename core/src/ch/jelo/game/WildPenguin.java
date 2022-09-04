package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static ch.jelo.game.Utils.DISTANCE_SPAWN;
import static com.badlogic.gdx.math.MathUtils.random;

public class WildPenguin extends GameObject {

    // Graphics
    private static Texture penguinImage = new Texture(Gdx.files.internal("penguin.png"));
    private static Texture heartImage = new Texture(Gdx.files.internal("love.png"));


    //Constants
    private final static float PENGUIN_WIDTH = 60;
    private final static float PENGUIN_HEIGHT = 60;
    private final static float DEFAULT_LOVE_DISTANCE = 70;

    private final static float SPEED = 1f;

    private final static float MOVING_DELAY_MAX = 3;
    private final static float STOP_DELAY_MAX = 1;
    private final static float MOVING_DELAY_MIN = 0.5f;
    private final static float STOP_DELAY_MIN = 0.5f;
    private final static float PROBA_MOVING = 0.6f;

    private final static float TIME_NEEDED_FOR_LOVE = 5;

    //attributs
    private float remainingTimeUntilLove = TIME_NEEDED_FOR_LOVE;
    ;

    private float remainingTimeUntilMovementChange = 0;
    private Vector2 direction = new Vector2();

    private boolean isFallingInLove = false;


    public WildPenguin(Vector2 playerPosition, float distance) {
        super(0, 0, PENGUIN_WIDTH, PENGUIN_HEIGHT);
        setRandomPosition(playerPosition, distance);
    }

    public float getLoveDistance() {
        return DEFAULT_LOVE_DISTANCE;
    }

    private void setRandomPosition(Vector2 playerPosition, float distance) {
        Vector2 spawn_relative = new Vector2().setToRandomDirection().scl(distance);
        setPosition(playerPosition.cpy().add(spawn_relative));
        setRandomMovement();
    }

    private void setRandomMovement() {
        if (random() < PROBA_MOVING) {
            remainingTimeUntilMovementChange = random(MOVING_DELAY_MIN, MOVING_DELAY_MAX);
            direction.setToRandomDirection();
        } else {
            remainingTimeUntilMovementChange = random(STOP_DELAY_MIN, STOP_DELAY_MAX);
            direction.set(0, 0);
        }

    }

    public boolean fallingInLove(float elapsedTime) {
        remainingTimeUntilLove -= elapsedTime;
        return remainingTimeUntilLove < 0;
    }

    @Override
    public void update(float delta, GameState gameState) {
        float distanceWithPlayer = gameState.player.position.dst(this.position);
        isFallingInLove = distanceWithPlayer <= this.getLoveDistance();
        if (distanceWithPlayer > 1000f) {
            this.setRandomPosition(gameState.player.position, DISTANCE_SPAWN);
        } else if (isFallingInLove) {
            boolean isInLove = fallingInLove(delta);
            if (isInLove) {
                // TODO add Follower
            }
        } else {
            remainingTimeUntilMovementChange-=delta;
            if(remainingTimeUntilMovementChange<=0f) {
                setRandomMovement();
            }
            this.position.mulAdd(direction,SPEED);

        }


        // TODO remove wild penguin
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(penguinImage, this.position.x, this.position.y, this.bounds.width, this.bounds.height);

        if (isFallingInLove) {
            batch.draw(heartImage, this.position.x + 20, this.position.y + 60,
                    20, 20);

        }
    }
}
