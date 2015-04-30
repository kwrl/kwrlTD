package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kwrl.GameLogic;

public class Ball extends GameObject {
	public Ball(Vector2 position, float radius) {
		super(new Texture("ball.png"));
		World world = GameLogic.getInstance().getWorld();
		CircleShape circle = new CircleShape();
		circle.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.shape = circle;
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set(position);
		
		this.body = world.createBody(bd);
		this.body.createFixture(fd);
		this.body.setUserData(this);
		fd.shape.dispose();
	}

	@Override
	protected void setFixtureValues() {
		this.friction = 0.5f;
		this.restitution = 1f;
		this.density = 10.0f;
	}
}
