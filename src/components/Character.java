package components;

import java.util.ArrayList;
import framework.Vector;
import javafx.geometry.Point3D;

public class Character extends Entity {
	public double horFOV = 45;// degrees to either side of center of view
	public double vertFOV = 40;
	public double cameraVA = 0; // 0 is birds eye view, - 90 behind
	public double cameraHA = 0; // 0 is right in front, -90 is on the left

	public double moveSpeed = 2;

	boolean GoingLeft = false;
	boolean GoingRight = false;
	boolean GoingForward = false;
	boolean GoingBack = false;
	boolean GoingUp = false;
	boolean GoingDown = false;
	boolean lookleft = false;
	boolean lookright = false;
	boolean lookUp = false;
	boolean lookDown = false;
	boolean holdJump = false;
	float b = 0.0f;
	double l = 0.0_5;
	public Character(double x, double y, double z, double height, double width) {
		Loc3D = new Point3D(x, y, z);
		hitBox = new HitBox3D(Loc3D, height, width, 0.0, new Vector(0, 0, 1));
	}

	public double getCameraHA() {
		return cameraHA;
		
	}

	public double getCameraVA() {
		return cameraVA;
	}

	public void updateControls(boolean GoingLeft, boolean GoingRight, boolean GoingForward, boolean GoingBack,
			boolean GoingUp, boolean GoingDown, boolean lookleft, boolean lookright, boolean lookUp, boolean lookDown,
			boolean holdJump) {
		this.GoingLeft = GoingLeft;
		this.GoingRight = GoingRight;
		this.GoingUp = GoingUp;
		this.GoingDown = GoingDown;
		this.GoingForward = GoingForward;
		this.GoingBack = GoingBack;
		this.lookUp = lookUp;
		this.lookright = lookright;
		this.lookleft = lookleft;
		this.lookDown = lookDown;
		this.holdJump = holdJump;

	}

	public void moveCamera(ArrayList<HitBox3D> hitBoxes) {
		if (holdJump) {
			jump(5);
		}
		if (GoingRight) {
			double xDir = Math.cos(Math.toRadians(-cameraHA)) * moveSpeed;
			double zDir = Math.sin(Math.toRadians(-cameraHA)) * moveSpeed;

			if (hitBoxes.isEmpty() == false) {
				boolean collide = false;
				for (HitBox3D hb : hitBoxes) {
					if (hb != null)
						if (hitBox.moved(-xDir, 0, -zDir).collidesWith(hb))
							collide = true;
				}
				if (collide == false)
					Loc3D = Loc3D.subtract(new Point3D(xDir, 0, zDir));
			} else
				Loc3D = Loc3D.subtract(new Point3D(xDir, 0, zDir));

		} else if (GoingLeft) {
			double xDir = Math.cos(Math.toRadians(-cameraHA - 180)) * moveSpeed;
			double zDir = Math.sin(Math.toRadians(-cameraHA - 180)) * moveSpeed;

			if (hitBoxes.isEmpty() == false) {
				boolean collide = false;
				for (HitBox3D hb : hitBoxes) {
					if (hb != null)
						if (hitBox.moved(-xDir, 0, -zDir).collidesWith(hb))
							collide = true;
				}
				if (collide == false)
					Loc3D = Loc3D.subtract(new Point3D(xDir, 0, zDir));
			} else
				Loc3D = Loc3D.subtract(new Point3D(xDir, 0, zDir));
		}
		if (GoingForward) {
			double xDir = Math.cos(Math.toRadians(-cameraHA + 90)) * moveSpeed;
			double zDir = Math.sin(Math.toRadians(-cameraHA + 90)) * moveSpeed;

			if (hitBoxes.isEmpty() == false) {
				boolean collide = false;
				for (HitBox3D hb : hitBoxes) {
					if (hb != null)
						if (hitBox.moved(-xDir, 0, -zDir).collidesWith(hb))
							collide = true;
				}
				if (collide == false)
					Loc3D = Loc3D.subtract(new Point3D(xDir, 0, zDir));
			} else
				Loc3D = Loc3D.subtract(new Point3D(xDir, 0, zDir));

		} else if (GoingBack) {
			double xDir = Math.cos(Math.toRadians(-cameraHA + 90)) * moveSpeed;
			double zDir = Math.sin(Math.toRadians(-cameraHA + 90)) * moveSpeed;

			if (hitBoxes.isEmpty() == false) {
				boolean collide = false;
				for (HitBox3D hb : hitBoxes) {
					if (hb != null)
						if (hitBox.moved(xDir, 0, zDir).collidesWith(hb))
							collide = true;
				}
				if (collide == false)
					Loc3D = Loc3D.add(new Point3D(xDir, 0, zDir));
			} else
				Loc3D = Loc3D.add(new Point3D(xDir, 0, zDir));
		}
		if (GoingDown) {
			Loc3D = Loc3D.subtract(new Point3D(0, moveSpeed, 0));
		} else if (GoingUp) {
			Loc3D = Loc3D.add(new Point3D(0, moveSpeed, 0));
		}
		if (lookleft) {
			cameraHA -= 1;
		} else if (lookright) {
			cameraHA += 1;
		}
		if (lookUp) {
			if (cameraVA > -90)
				cameraVA -= 1;
		} else if (lookDown) {
			if (cameraVA < 90)
				cameraVA += 1;
		}
		// System.out.println(Loc3D.getX() +", "+ Loc3D.getY()+", "+Loc3D.getZ()
		// );
		hitBox.updatePos(Loc3D, new Vector(0, 0, 1));
	}
}