package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Ground extends RectangleToken {

	@Override
	public void setFixtureValues() {
		this.friction = 0.01f;
		this.density = 1f;
		this.restitution = 0.8f;

	}

	public Ground(Vector2 position, Vector2 dimensions) {
		super(new Texture("stone.png"), position, dimensions,
				BodyType.StaticBody);
	}

}
