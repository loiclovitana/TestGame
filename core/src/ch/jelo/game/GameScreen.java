package ch.jelo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import static ch.jelo.game.Utils.VIEW_WIDTH;
import static ch.jelo.game.Utils.VIEW_HEIGHT;

import static com.badlogic.gdx.math.MathUtils.random;

public class GameScreen implements Screen {

    // engine related
    private final TestGame game;

    private final GameState gameState;

    private OrthographicCamera camera;



    // Sprites
    private Texture playerImage;
    private Texture snowImage;
    private Texture heartImage;

    public GameScreen(TestGame game) {
        this.game = game;
        gameState=new GameState();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);


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
        gameState.update(delta);
        // clear screen
        ScreenUtils.clear(0.3f, 0.3f, 1, 1);
        camera.position.x = gameState.player.position.x + gameState.player.bounds.width / 2;
        camera.position.y = gameState.player.position.y + gameState.player.bounds.height / 2;
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // DRAW START
        game.batch.begin();
        game.batch.draw(snowImage, -1000, -1000, 2000, 2000);

        // draw all gameobject
        for(GameObject  gobject : gameState.getAllObject()){
            game.batch.draw(playerImage, gobject.position.x, gobject.position.y, gobject.bounds.width, gobject.bounds.height);
        }

        // draw heart
        for (WildPenguin wildPenguin : gameState.wildPenguins) {
            float distanceWithPlayer = gameState.player.position.dst(wildPenguin.position);
            if (distanceWithPlayer <= wildPenguin.getLoveDistance()) {
                game.batch.draw(heartImage, wildPenguin.position.x + 20, wildPenguin.position.y + 60,
                        20, 20); // FIXME heart should be a game object in itself

            }
        }


        game.batch.end();
        // DRAW END




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
