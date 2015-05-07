package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.kwrl.GameLogic;
import com.kwrl.models.factories.GameObjectFactory;

public class SpawnPoint extends GameObject {

	protected GameObjectFactory factory;
	protected float lastSpawn, spawnInterval;
	protected Timer timer;
	protected boolean spawning;

	public SpawnPoint(Vector2 position, Vector2 dimensions, GameObjectFactory factory) {
		super(new Texture("stone.png"));
		World world = GameLogic.getInstance().getWorld();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(dimensions.x, dimensions.y);

		FixtureDef fd = new FixtureDef();
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.isSensor = true;
		fd.shape = shape;

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(position);

		this.body = world.createBody(bd);
		this.body.createFixture(fd);
		this.body.setUserData(this);
		fd.shape.dispose();
		
		this.factory = factory;
		
		startSpawning(5);
	}
	
	public void startSpawning(int interval) {
		timer = new Timer();
		spawning = true;
		timer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				if(spawning) {
					factory.createInstance(body.getPosition());
					timer.scheduleTask(this, 5);
				}
			}
		}, 5);
	}
	
	public void stopSpawning() {
		spawning = false;
	}
	
	@Override
	protected void setFixtureValues() {
		this.density = 0f;
		this.friction = 10f;
		this.restitution = 0f;
	}

	@Override
	public void draw(Batch batch, Body body) {
		super.draw(batch, body);
	}
}
