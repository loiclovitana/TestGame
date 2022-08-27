package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.math.MathUtils.random;
import static java.lang.Math.sqrt;

public class GameScreen implements Screen {

    // static engine
    private static final float VIEW_WIDTH = 800;
    private static final float VIEW_HEIGHT = 600;
    public static final float DISTANCE_SPAWN = (float) sqrt(VIEW_HEIGHT*VIEW_HEIGHT+VIEW_WIDTH*VIEW_WIDTH) / 2 + 50;



    // engine related
    private final TestGame game;
    private OrthographicCamera camera;

    // static GAME
    private static final int MAX_WILD_PENGUIN = 20;
    private static final float CHANCE_SPAWN_WILD_PENGUIN = 0.3f;
    private static final float TIME_BETWEEN_SPAWN_TRY = 1f; //time in second

    // Objects
    private Player player;
    private Array<Follower> followers;
    private Array<WildPenguin> wildPenguins;

    private float spawnWildCooldown;

    // Sprites
    private Texture playerImage;
    private Texture snowImage;
    private Texture heartImage;

    public GameScreen(TestGame game) {
        this.game = game;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);

        // Add the first entities
        player = new Player(0, 0);

        followers = new Array<Follower>();
        wildPenguins = new Array<WildPenguin>();
        spawnWildCooldown = 0f;

        // load the texture
        playerImage = new Texture(Gdx.files.internal("penguin.png"));
        snowImage = new Texture(Gdx.files.internal("snow.jpg"));
        heartImage = new Texture(Gdx.files.internal("love.png"));

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        // clear screen
        ScreenUtils.clear(0.3f, 0.3f, 1, 1);
        camera.position.x = player.position.x + player.bounds.width / 2;
        camera.position.y = player.position.y + player.bounds.height / 2;
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

         // DRAW START
        game.batch.begin();
        game.batch.draw(snowImage, -1000, -1000, 2000, 2000);

        // draw all wild penguin
        for (WildPenguin wildPenguin : wildPenguins) {
            game.batch.draw(playerImage, wildPenguin.position.x, wildPenguin.position.y,
                    wildPenguin.bounds.width, wildPenguin.bounds.height);
        }


        game.batch.draw(playerImage, player.position.x, player.position.y, player.bounds.width, player.bounds.height);

        // draw heart
        for (WildPenguin wildPenguin : wildPenguins) {
            float distanceWithPlayer = player.position.dst(wildPenguin.position);
            if (distanceWithPlayer <= wildPenguin.getLoveDistance()) {
                game.batch.draw(heartImage, wildPenguin.position.x+20, wildPenguin.position.y+60,
                        20, 20); // FIXME heart should be a game object in itself

            }
        }


        game.batch.end();
        // DRAW END

        // Update of Player
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.position.x -= player.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.position.x += player.getSpeed() * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            player.position.y += player.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.position.y -= player.getSpeed() * Gdx.graphics.getDeltaTime();


        // Update of Wild Penguin

        if (wildPenguins.size < MAX_WILD_PENGUIN && spawnWildCooldown < 0) {
            spawnWildCooldown = TIME_BETWEEN_SPAWN_TRY;
            if (random() < CHANCE_SPAWN_WILD_PENGUIN) {
                wildPenguins.add(new WildPenguin(player.position, DISTANCE_SPAWN));
                System.out.println(wildPenguins.size);
            }

        } else {
            spawnWildCooldown -= Gdx.graphics.getDeltaTime();
        }

        for (
                WildPenguin wildPenguin : wildPenguins) {
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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
