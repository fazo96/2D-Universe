package com.me.game;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GravityPoint {

	public static void destroyAll() {
		for (GravityPoint p : points)
			p.destroyASAP = true;
	}

	protected boolean destroyed = false, destroyASAP = false;
	private double x, y;
	private double r;
	private static double simulationSpeed = 0.000005;
	private double density = 0.5, accX = 0, accY = 0, speedX = 0, speedY = 0;
	private static ArrayList<GravityPoint> points = new ArrayList<GravityPoint>();

	public void move(double offsetX, double offsetY) {
		x += offsetX;
		y += offsetY;
	}

	public static void drawAll(ShapeRenderer g) {
		for (GravityPoint c : GravityPoint.getPoints()) {
			c.draw(g);
		}
	}

	public void draw(ShapeRenderer g) {
		g.setColor(1, 1, 1, 1);
		g.circle((float) getX(), (float) getY(), (float) getR());
		g.circle((float) (getX()), (float) (getY()), 1);
	}

	public static void updateAll() {
		try {
			for (GravityPoint c : points) {
				c.checkDestroy();
			}
		} catch (ConcurrentModificationException ex) {
			// System.out.println("ConcurrentModificationExeption caught successfully");
		}
		for (GravityPoint c : points) {
			c.updateGravity();
		}
		for (GravityPoint c : points) {
			c.updatePosition();
		}
		for(GravityPoint c: points){
			c.checkForCollisions();
		}
	}

	public GravityPoint(double x, double y, double r, double density) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.density = density;
		points.add(this);
	}

	public GravityPoint(double x, double y, double r, double density,
			double speedX, double speedY) {
		this(x, y, r, density);
		this.speedX = speedX;
		this.speedY = speedY;
	}

	public void destroy() {
		this.destroyASAP = true;
		destroyed = true;
		points.remove(this);
	}

	public double getGravityForce() {
		return density * Math.PI * Math.pow(getR(), 2);
	}

	public boolean overlaps(GravityPoint c) {
		return distance(c) < (getR() + c.getR());
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void checkDestroy() {
		if (destroyASAP) {
			destroy();
		}
	}

	public void updatePosition() {
		// apply attraction
		for (GravityPoint p : points) {
			speedX += accX;
			speedY += accY;
		}
		move(speedX, speedY);
	}

	public void checkForCollisions() {
		for (GravityPoint p : points)
			if (p != this && overlaps(p) && getR() >= p.getR()) { // collision
				double areaThis = Math.PI * Math.pow(this.getR(), 2);
				double areaP = Math.PI * Math.pow(p.getR(), 2);
				double areaTot = areaThis + areaP;
				double radiusNew = Math.sqrt(areaTot / Math.PI);
				if (radiusNew <= 0)
					radiusNew = 3;
				this.setR(radiusNew);
				this.density = (areaThis * this.density + areaP * p.density)
						/ areaTot;
				if (this.density <= 0)
					this.density = 0.001;
				this.accX = (areaP * p.accX + areaThis * this.accX) / areaTot;
				this.accY = (areaP * p.accY + areaThis * this.accY) / areaTot;
				this.speedX = (areaP * p.speedX + areaThis * this.speedX)
						/ areaTot;
				this.speedY = (areaP * p.speedY + areaThis * this.speedY)
						/ areaTot;
				p.destroyASAP = true; // adios, p
			}
	}

	public void updateGravity() {
		for (GravityPoint p : points) {
			if (density == 0) {
				return;
			}
			if (p == this) {
				continue;
			}
			double dist = distance(p);
			if (p.density == 0) {
				continue;
			}
			// calculate acceleration
			double accMul = 5 * p.getGravityForce() / dist;
			double dx = (p.getX() - getX()) / p.getR();
			dx = dx < -1 ? -1 : dx > 1 ? 1 : dx;
			double dy = (p.getY() - getY()) / p.getR();
			dy = dy < -1 ? -1 : dy > 1 ? 1 : dy;
			accX = dx * accMul * simulationSpeed;
			accY = dy * accMul * simulationSpeed;
		}
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double distance(GravityPoint c) {
		return Math.sqrt(Math.pow(c.getX() - getX(), 2)
				+ Math.pow(c.getY() - getY(), 2));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAccX() {
		return accX;
	}

	public void setAccX(double accX) {
		this.accX = accX;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public static ArrayList<GravityPoint> getPoints() {
		return points;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}
}
