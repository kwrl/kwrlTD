package com.kwrl.models.abstracts;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.kwrl.GameLogic;

public abstract class Token extends Box2DSprite {
	protected BodyType type = BodyType.DynamicBody;
	protected Body body;
	
	protected float density, friction, restitution;
	protected float health = 100f;
	
	protected boolean isSensor = false;
	
	protected void setFixtureValues() {
	}
	
	public Token(Texture tex) {
		super(tex);
		GameLogic.getInstance().addGameObject(this);
		setFixtureValues();
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
