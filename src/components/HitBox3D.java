package components;

import framework.Vector;
import javafx.geometry.Point3D;

public class HitBox3D {

	Point3D Origin;
	public double height, width, depth;
	Vector direction;// default is north, refers to direction to forward

	public HitBox3D(Point3D Origin, double height, double width, double depth, Vector direction) {
		this.Origin = Origin;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.direction = direction;
	}

	public void updatePos(Point3D newOrigin, Vector direction) {
		Origin = newOrigin;
		this.direction = direction;
	}
	public HitBox3D moved(double x, double y , double z){
		return (new HitBox3D(Origin.add(new Point3D(x, y, z)),height,width,depth,direction));
	}

	public boolean collidesWith(HitBox3D other) {

		if ((other.depth == 0 || other.width == 0) && (depth == 0 || width == 0)) {
			if (width / 2 + other.width / 2 > (Math.sqrt(Math.pow(Origin.getX() - other.Origin.getX(), 2)
					+ Math.pow(Origin.getZ() - other.Origin.getZ(), 2)))) {
				if (((Origin.getY() - height / 2 < other.Origin.getY() + other.height / 2)//bottom lower top and
						&& (Origin.getY() - height / 2 > other.Origin.getY() - other.height / 2))//bottom higher bottom
						|| ((Origin.getY() + height / 2 > other.Origin.getY() - other.height / 2)//top higher bottom
								&& (Origin.getY() + height / 2 < other.Origin.getY() + other.height / 2)))//top lower top
					return true;
			}
		} else if (depth == 0 || width == 0) {

		}
		return false;
	}
}
