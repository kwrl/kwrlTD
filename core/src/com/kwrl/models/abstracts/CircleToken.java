package com.kwrl.models.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kwrl.GameLogic;

public class CircleToken extends Token {

	public CircleToken(Texture tex, Vector2 position, float radius) {
		super(tex);
		this.body = createCircleBody(position, radius);
	}

	public CircleToken(Texture tex, Vector2 position, float radius,
			boolean isSensor) {
		super(tex);
		this.isSensor = isSensor;
		this.body = createCircleBody(position, radius);
	}

	public CircleToken(Texture tex, Vector2 position, float radius,
			BodyType type) {
		super(tex);
		this.type = type;
		this.body = createCircleBody(position, radius);
	}

	public CircleToken(Texture tex, Vector2 position, float radius,
			BodyType type, boolean isSensor) {
		super(tex);
		this.type = type;
		this.isSensor = isSensor;
		this.body = createCircleBody(position, radius);
	}

	protected Body createCircleBody(Vector2 position, float radius) {
		World world = GameLogic.getInstance().getWorld();
		CircleShape circle = new CircleShape();
		circle.setRadius(radius);

		FixtureDef fd = new FixtureDef();
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.shape = circle;
		fd.isSensor = isSensor;

		BodyDef bd = new BodyDef();
		bd.type = type;
		bd.position.set(position);

		Body body = world.createBody(bd);
		body.createFixture(fd);
		body.setUserData(this);
		fd.shape.dispose();
		return body;
	}
}
