package ch.jelo.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.HashSet;
import java.util.Set;
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
    private SortedSet<GameObject> gameObjects;
    public Player player;
    public Array<WildPenguin> wildPenguins;



    // objects to add next frame or to delete

    private Set<GameObject> objectToRemove;
    private Set<GameObject> objectToAdd;


    private float spawnWildCooldown;

    public GameState(){
        // Add the first entities
        player = new Player(0, 0);
        gameObjects =new TreeSet<GameObject>();
        gameObjects.add(player);

        wildPenguins = new Array<WildPenguin>();
        spawnWildCooldown = 0f;

        objectToRemove = new HashSet<GameObject>();
        objectToAdd= new HashSet<GameObject>();

    }


    public void update(float delta){

        for(GameObject gameObject: gameObjects){
            gameObject.update(delta,this);
        }


        // Create Wild Penguins
        if (wildPenguins.size < MAX_WILD_PENGUIN && spawnWildCooldown < 0) {
            spawnWildCooldown = TIME_BETWEEN_SPAWN_TRY;
            if (random() < CHANCE_SPAWN_WILD_PENGUIN) {
                WildPenguin newPenguin = new WildPenguin(player.position, DISTANCE_SPAWN);
                wildPenguins.add(newPenguin);
                addGameObject(newPenguin);
            }
        } else {
            spawnWildCooldown -= delta;
        }


        // Add and remove objects
        gameObjects.removeAll(objectToRemove);
        gameObjects.addAll(objectToAdd);


    }

    public void addGameObject(GameObject newObject){
        objectToAdd.add(newObject);
    }

    public void removeGameObject(GameObject deadObject){
        objectToRemove.add(deadObject);
    }



    public SortedSet<GameObject> getAllObject(){
        return gameObjects;
    }


    public void draw(SpriteBatch batch) {
        for(GameObject  gobject : this.getAllObject()){
            gobject.draw(batch);
        }
    }
}
