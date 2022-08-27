package ch.jelo.game;

public class Follower extends GameObject{

    private float loveDistance = 70f;

    public Follower(float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    public float getLoveDistance() {
        return loveDistance;
    }

}
