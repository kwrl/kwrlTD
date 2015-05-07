package com.kwrl;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.kwrl.models.Ball;
import com.kwrl.models.GameObject;
import com.kwrl.models.Weapon;

public class GameLogic implements ContactListener {
	private static GameLogic instance = null;
	private Array<GameObject> gameObjects;
	private World world;
	
	public static GameLogic getInstance() {
		if(GameLogic.instance==null) {
			GameLogic.instance = new GameLogic();
		}
		return GameLogic.instance;
	}
	
	public GameLogic() {
		this.world = new World(new Vector2(0.0f, -5f), true);
		this.world.setContactListener(this);
		this.gameObjects = new Array<GameObject>();
	}

	@Override
	public void beginContact(Contact contact) {
		GameObject a, b;
		a = (GameObject)contact.getFixtureA().getBody().getUserData();
		b = (GameObject)contact.getFixtureB().getBody().getUserData();
		
		if(a instanceof Weapon.DangerZone && b instanceof Ball) {
			
		} else if(b instanceof Weapon.DangerZone && a instanceof Ball) {
			
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
	
	public World getWorld() {
		return world;
	}
	
	public void addGameObject(GameObject obj) {
		this.gameObjects.add(obj);
	}
	
	public Array<GameObject> getGameObjects() {
		return this.gameObjects;
	}
	
}
