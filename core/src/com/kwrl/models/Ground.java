package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.kwrl.models.abstracts.RectangleToken;

public class Ground extends RectangleToken {

	@Override
	public void setFixtureValues() {
		this.friction = 1f;
		this.density = 1f;
		this.restitution = 1f;

	}

	public Ground(Vector2 position, Vector2 dimensions) {
		super(new Texture("stone.png"), position, dimensions,
				BodyType.StaticBody);
	}

}
