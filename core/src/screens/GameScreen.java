package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.kwrl.GameLogic;
import com.kwrl.KwrlTD;
import com.kwrl.models.GameObject;
import com.kwrl.models.Ground;
import com.kwrl.models.SpawnPoint;
import com.kwrl.models.Weapon;
import com.kwrl.models.factories.BallFactory;

public class GameScreen implements Screen, InputProcessor {
	private Vector2 touchDown;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private SpriteBatch sb;
	private final KwrlTD game;
	private final int PPM = 12;

	public GameScreen() {
		this.game = (KwrlTD) Gdx.app.getApplicationListener();
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / PPM,
				Gdx.graphics.getHeight() / PPM);
		camera.update();
		sb = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		World world = GameLogic.getInstance().getWorld();
		Array<GameObject> gameObjects = GameLogic.getInstance()
				.getGameObjects();
		float dt = Math.min(delta, 1 / 30f);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		for (GameObject obj : gameObjects) {
			obj.draw(sb, obj.getBody());
		}
		sb.end();

		// debugRenderer.render(world, camera.combined);
		world.step(dt, 6, 6);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Vector2 pos = screenPosToWorld(Gdx.input.getX(), Gdx.input.getY());
		switch(keycode) {
		case Keys.SPACE: new SpawnPoint(pos, new Vector2(2f, 2f), new BallFactory(1f)); break;
		case Keys.UP: new Weapon(pos, 1f, 3f); break;
		}
		camera.update();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchDown = screenPosToWorld(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		new Ground(touchDown, screenPosToWorld(screenX, screenY), 0.1f);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private Vector2 screenPosToWorld(int screenX, int screenY) {
		return new Vector2(screenX / PPM, (Gdx.graphics.getHeight() - screenY)
				/ PPM);
	}

}
