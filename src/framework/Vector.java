package framework;

public class Vector {
	public double x, y, z;

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector add(Vector addedVector) {
		x += addedVector.x;
		y += addedVector.y;
		z += addedVector.z;
		return this;
	}

	public Vector subtract(Vector subtractedVector) {
		x -= subtractedVector.x;
		y -= subtractedVector.y;
		z -= subtractedVector.z;
		return this;
	}

	public Vector multiply(double multipliedNumber) {
		x *= multipliedNumber;
		y *= multipliedNumber;
		z *= multipliedNumber;
		return this;
	}
	public Vector cross(Vector crossVector) {
		//vector right angle positive to each other vector
		Vector crossProduct = new Vector(this.x,this.y,this.z);
		crossProduct.x =this.y*crossVector.z - this.z*crossVector.y;
		crossProduct.y =this.z*crossVector.x - this.x*crossVector.z;
		crossProduct.z =this.x*crossVector.y - this.y*crossVector.x;
		return crossProduct;
	}

	public double dot(Vector dotVector) {
		double magnitude;
		magnitude = this.x * dotVector.x + this.y * dotVector.y + this.z * dotVector.z;
		return magnitude;
	}
	public Vector standardify(){
		double r = Math.sqrt(x*x+y*y+z*z);
		return(new Vector(x*x/(r*r),y*y/(r*r),z*z/(r*r)));
	}
}
