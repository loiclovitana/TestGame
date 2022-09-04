package ch.jelo.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

import static ch.jelo.game.Utils.DISTANCE_SPAWN;
import static com.badlogic.gdx.math.MathUtils.random;

public class GameState {

    // static GAME
    private static final int MAX_WILD_PENGUIN = 5;
    private static final float CHANCE_SPAWN_WILD_PENGUIN = 0.3f;
    private static final float TIME_BETWEEN_SPAWN_TRY = 1f; //time in second

    // Objects
    private Set<GameObject> gameObjects;
    public Player player;
    public int wildPenguinsSize;



    // objects to add next frame or to delete

    private Set<GameObject> objectToRemove;
    private Set<GameObject> objectToAdd;


    private float spawnWildCooldown;

    public GameState(){
        // Add the first entities
        player = new Player(0, 0);
        gameObjects =new HashSet<GameObject>();
        gameObjects.add(player);

        wildPenguinsSize=0;
        spawnWildCooldown = 0f;

        objectToRemove = new HashSet<GameObject>();
        objectToAdd= new HashSet<GameObject>();

    }


    public void update(float delta){

        for(GameObject gameObject: gameObjects){
            gameObject.update(delta,this);
        }


        // Create Wild Penguins
        if (wildPenguinsSize < MAX_WILD_PENGUIN && spawnWildCooldown < 0) {
            spawnWildCooldown = TIME_BETWEEN_SPAWN_TRY;
            if (random() < CHANCE_SPAWN_WILD_PENGUIN) {
                WildPenguin newPenguin = new WildPenguin(player.position, DISTANCE_SPAWN);
                wildPenguinsSize+=1;
                addGameObject(newPenguin);
            }
        } else {
            spawnWildCooldown -= delta;
        }
        if(!objectToRemove.isEmpty()) {
            System.out.println(objectToRemove);
            System.out.println(gameObjects);
            System.out.println(objectToAdd);
        }
        // Add and remove objects
        gameObjects.removeAll(objectToRemove);
        objectToRemove.clear();
        gameObjects.addAll(objectToAdd);
        objectToAdd.clear();


    }

    public void addGameObject(GameObject newObject){
        objectToAdd.add(newObject);
    }

    public void removeGameObject(GameObject deadObject){
        objectToRemove.add(deadObject);
        if (deadObject instanceof WildPenguin){
            wildPenguinsSize-=1;
        }
    }



    public Set<GameObject> getAllObject(){
        return gameObjects;
    }


    public void draw(SpriteBatch batch) {
        List<GameObject> sortedObjects = new ArrayList(this.gameObjects);
        Collections.sort(sortedObjects,GameObject.GAME_OBJECT_DRAW_COMPARATOR);
        for(GameObject  gobject :  sortedObjects){
            gobject.draw(batch);
        }
    }
}
