package components;

import javafx.geometry.Point3D;

public class LightSource {
	
	public Point3D pos;
	public double intensity;
	
	public LightSource(Point3D pos, double intensity){
		this.pos = pos;
		this.intensity = intensity;
	}

}
