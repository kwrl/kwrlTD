package com.kwrl;

import java.util.ArrayList;

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
	private ArrayList<GameObject> gameObjects;
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
		this.gameObjects = new ArrayList<GameObject>();
	}

	@Override
	public void beginContact(Contact contact) {
		GameObject a, b;
		a = (GameObject)contact.getFixtureA().getBody().getUserData();
		b = (GameObject)contact.getFixtureB().getBody().getUserData();
		
		if(a instanceof Weapon.DangerZone && b instanceof Ball) {
			((Weapon.DangerZone)a).addTarget((Ball)b);
		} else if(b instanceof Weapon.DangerZone && a instanceof Ball) {
			((Weapon.DangerZone)b).addTarget((Ball)a);
		}
	}

	@Override
	public void endContact(Contact contact) {
		GameObject a, b;
		a = (GameObject)contact.getFixtureA().getBody().getUserData();
		b = (GameObject)contact.getFixtureB().getBody().getUserData();
		
		if(a instanceof Weapon.DangerZone && b instanceof Ball) {
			((Weapon.DangerZone)a).removeTarget((Ball)b);
		} else if(b instanceof Weapon.DangerZone && a instanceof Ball) {
			((Weapon.DangerZone)b).removeTarget((Ball)a);
		}
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
	
	public void removeGameObject(GameObject obj) {
		this.world.destroyBody(obj.getBody());
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return this.gameObjects;
	}
	
}
