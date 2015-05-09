package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kwrl.GameLogic;

public class RectangleToken extends Token {

	public RectangleToken(Texture tex, Vector2 position, Vector2 dimensions) {
		super(tex);
		this.body = createRectangleBody(position, dimensions);
	}

	public RectangleToken(Texture tex, Vector2 position, Vector2 dimensions,
			boolean isSensor) {
		super(tex);
		this.isSensor = isSensor;
		this.body = createRectangleBody(position, dimensions);
	}

	public RectangleToken(Texture tex, Vector2 position, Vector2 dimensions,
			BodyType type) {
		super(tex);
		this.type = type;
		this.body = createRectangleBody(position, dimensions);
	}

	public RectangleToken(Texture tex, Vector2 position, Vector2 dimensions,
			BodyType type, boolean isSensor) {
		super(tex);
		this.type = type;
		this.isSensor = isSensor;
		this.body = createRectangleBody(position, dimensions);
	}

	protected Body createRectangleBody(Vector2 position, Vector2 dimensions) {
		World world = GameLogic.getInstance().getWorld();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(dimensions.x, dimensions.y);

		FixtureDef fd = new FixtureDef();
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.shape = shape;
		fd.isSensor = isSensor;

		BodyDef bd = new BodyDef();
		bd.type = type; 
		bd.position.set(position);
		body = world.createBody(bd);
		body.createFixture(fd);
		body.setUserData(this);
		fd.shape.dispose();

		return body;
	}
}
