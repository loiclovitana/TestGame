package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static ch.jelo.game.Utils.DISTANCE_SPAWN;
import static com.badlogic.gdx.math.MathUtils.random;

public class GameState {

    // static GAME
    private static final int MAX_WILD_PENGUIN = 20;
    private static final float CHANCE_SPAWN_WILD_PENGUIN = 0.3f;
    private static final float TIME_BETWEEN_SPAWN_TRY = 1f; //time in second

    // Objects
    private SortedSet<GameObject> objects;
    public Player player;
    private Array<Follower> followers;
    public Array<WildPenguin> wildPenguins;


    private float spawnWildCooldown;

    public GameState(){
        // Add the first entities
        player = new Player(0, 0);
        objects=new TreeSet<GameObject>();
        objects.add(player);

        followers = new Array<Follower>();
        wildPenguins = new Array<WildPenguin>();
        spawnWildCooldown = 0f;
    }


    public void update(float delta){

        for(GameObject gameObject: objects){
            gameObject.update(delta,this);
        }


        // Update of Wild Penguin

        if (wildPenguins.size < MAX_WILD_PENGUIN && spawnWildCooldown < 0) {
            spawnWildCooldown = TIME_BETWEEN_SPAWN_TRY;
            if (random() < CHANCE_SPAWN_WILD_PENGUIN) {
                WildPenguin newPenguin = new WildPenguin(player.position, DISTANCE_SPAWN);
                wildPenguins.add(newPenguin);
                objects.add(newPenguin);
                System.out.println(wildPenguins.size);
            }

        } else {
            spawnWildCooldown -= Gdx.graphics.getDeltaTime();
        }

        for (WildPenguin wildPenguin : wildPenguins) {
            float distanceWithPlayer = player.position.dst(wildPenguin.position);
            if (distanceWithPlayer > 1000f) {
                wildPenguin.setRandomPosition(player.position, DISTANCE_SPAWN);
            } else if (distanceWithPlayer <= wildPenguin.getLoveDistance()) {
                boolean isInLove = wildPenguin.fallingInLove(Gdx.graphics.getDeltaTime());
                if (isInLove) {
                    // TODO add Follower
                }
            }
        }

        // TODO remove wild penguin
    }



    public SortedSet<GameObject> getAllObject(){
        return objects;
    }





}
