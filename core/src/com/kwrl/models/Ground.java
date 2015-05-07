package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kwrl.GameLogic;

public class Ground extends GameObject {
	protected float width;

	public Ground(Vector2 start, Vector2 stop, float f) {
		super(new Texture("stone.png"));
		Vector2 vec = stop.sub(start);
		World world = GameLogic.getInstance().getWorld();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(vec.len() / 2f, f);

		FixtureDef fd = new FixtureDef();
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.shape = shape;

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(start);
		body = world.createBody(bd);
		body.setTransform(start.add(vec.scl(0.5f)), vec.angleRad());
		body.createFixture(fd);
		body.setUserData(this);
		fd.shape.dispose();
	}

	@Override
	protected void setFixtureValues() {
		this.friction = 0.01f;
		this.density = 1f;
		this.restitution = 0.8f;
	}

}
