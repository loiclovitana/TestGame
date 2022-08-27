package ch.jelo.game;

import com.badlogic.gdx.math.Vector2;

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
}