package com.kwrl.models;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.kwrl.GameLogic;

public abstract class GameObject extends Box2DSprite {
	protected float density, friction, restitution;
	protected Body body;
	
	protected abstract void setFixtureValues();
	
	public GameObject(Texture tex) {
		super(tex);
		this.setFixtureValues();
		GameLogic.getInstance().addGameObject(this);
	}
	
	public Body getBody() {
		return this.body;
	}
}
