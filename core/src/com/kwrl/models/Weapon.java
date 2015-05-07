package com.kwrl.models;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kwrl.GameLogic;

public class Weapon extends GameObject {
	protected DangerZone dangerZone;
	protected Body rangeSector;
	protected float range, radius;
	
	public Weapon(Vector2 position, float radius, float range) {
		super(new Texture("weapon.png"));
			World world = GameLogic.getInstance().getWorld();
		CircleShape circle = new CircleShape();
		circle.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		fd.shape = circle;
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(position);
		
		this.body = world.createBody(bd);
		this.body.createFixture(fd);
		this.body.setUserData(this);
		this.range = range;
		this.radius = radius;
		fd.shape.dispose();
		
		this.dangerZone = new DangerZone(this);
	}

	@Override
	protected void setFixtureValues() {
	}
	
	public class DangerZone extends GameObject {
		protected ArrayList<Ball> targets = new ArrayList<Ball>();
		
		public DangerZone(Weapon weapon) {
			super(new Texture("dangerzone.png"));
			World world = GameLogic.getInstance().getWorld();
			CircleShape circle = new CircleShape();
			circle.setRadius(weapon.range);
			
			FixtureDef fd = new FixtureDef();
			fd.density = density;
			fd.friction = friction;
			fd.restitution = restitution;
			fd.shape = circle;
			fd.isSensor = true;
			
			BodyDef bd = new BodyDef();
			bd.type = BodyType.StaticBody;
			bd.position.set(weapon.body.getPosition());
			
			this.body = world.createBody(bd);
			this.body.createFixture(fd);
			this.body.setUserData(this);
			fd.shape.dispose();
		}

		@Override
		protected void setFixtureValues() {
			this.restitution = 1.0f;
			this.density = 1.0f;
			this.friction = 1.0f;
		}
		
		public void addTarget(Ball ball) {
			this.targets.add(ball);
		}
		
		public void removeTarget(Ball ball) {
			this.targets.remove(ball);
		}
	}
}
