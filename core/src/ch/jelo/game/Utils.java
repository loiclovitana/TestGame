package ch.jelo.game;

import static java.lang.Math.sqrt;

public class Utils {


    public static final float VIEW_HEIGHT = 600;
    // static engine
    public static final float VIEW_WIDTH = 800;
    public static final float DISTANCE_SPAWN = (float) sqrt(VIEW_HEIGHT * VIEW_HEIGHT + VIEW_WIDTH * VIEW_WIDTH) / 2 + 50;
}
