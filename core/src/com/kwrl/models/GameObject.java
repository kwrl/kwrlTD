package com.kwrl.models;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.kwrl.GameLogic;

public abstract class GameObject extends Box2DSprite {
	protected Body body;
	
	protected float density, friction, restitution;
	protected float health = 100f;
	
	protected abstract void setFixtureValues();
	
	public GameObject(Texture tex) {
		super(tex);
		this.setFixtureValues();
		GameLogic.getInstance().addGameObject(this);
	}
	
	public Body getBody() {
		return this.body;
	}
	
	public void takeDamage(float damage) {
		this.health-=damage;
		if(health<=0) {
			GameLogic.getInstance().removeGameObject(this);
		}
	}
}
