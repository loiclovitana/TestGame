package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import static ch.jelo.game.Utils.DISTANCE_SPAWN;

public class WildPenguin extends GameObject {
    private final static float PENGUIN_WIDTH=60;
    private final static float PENGUIN_HEIGHT =60;
    private final static float DEFAULT_LOVE_DISTANCE =70;

    private final static float TIME_NEEDED_FOR_LOVE = 5;

    private float remainingTimeUntilLove;



    public WildPenguin(Vector2 playerPosition, float distance) {
        super(0, 0, PENGUIN_WIDTH, PENGUIN_HEIGHT);
        setRandomPosition(playerPosition,distance);
        remainingTimeUntilLove= TIME_NEEDED_FOR_LOVE;
    }
   public float getLoveDistance(){
        return DEFAULT_LOVE_DISTANCE;
   }
    public  void setRandomPosition(Vector2 playerPosition, float distance){
        Vector2 spawn_relative = new Vector2().setToRandomDirection().scl(distance);
        setPosition(playerPosition.cpy().add(spawn_relative));
    }

    public boolean fallingInLove(float elapsedTime){
        remainingTimeUntilLove-=elapsedTime;
        return remainingTimeUntilLove<0;
    }

    @Override
    public void update(float delta, GameState gameState) {
        float distanceWithPlayer = gameState.player.position.dst(this.position);
        if (distanceWithPlayer > 1000f) {
            this.setRandomPosition(gameState.player.position, DISTANCE_SPAWN);
        } else if (distanceWithPlayer <= this.getLoveDistance()) {
            boolean isInLove = this.fallingInLove(delta);
            if (isInLove) {
                // TODO add Follower
            }
        }


        // TODO remove wild penguin
    }
}
