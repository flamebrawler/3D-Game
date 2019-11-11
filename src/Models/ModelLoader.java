package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import components.HitBox3D;
import framework.Vector;
import javafx.geometry.Point3D;

public class ModelLoader {
	String filename = "cube";

	public static Model Load(String filename, double scale) {
		Model m = new Model();

		try {
			File model = new File("C:/Users/flame/workspace/3d Stuff/src/resources/" + filename + ".obj");
			Scanner sc = new Scanner(model);
			double highest = 0, lowest = 0, forwardest = 0, backest = 0, rightest = 0, leftest = 0;
			while (sc.hasNext()) {
				String resource = sc.next();
				double x, y, z;
				if (resource.contentEquals("v")) {
					x = sc.nextDouble();
					if (x < leftest)
						leftest = x;
					else if (x > rightest)
						rightest = x;
					y = sc.nextDouble();
					if (y > highest)
						highest = y;
					else if (y < lowest)
						lowest = y;
					z = sc.nextDouble();
					if (z < forwardest)
						forwardest = z;
					else if (z > backest)
						backest = z;
					m.vertices.add(new Point3D(-scale * x, scale * y, -scale * z));
					// System.out.println(x + ", " + y + ", " + z);

				} else if (resource.contentEquals("vn")) {

					x = sc.nextDouble();
					y = sc.nextDouble();
					z = sc.nextDouble();
					m.normals.add(new Vector(x, y, z));
					// System.out.println(x + ", " + y + ", " + z);

				} else if (resource.contentEquals("vt")) {

					double val1 = sc.nextDouble();
					double val2 = sc.nextDouble();
					m.texture.add(new Point3D(val1, val2, 0));

				} else if (resource.contentEquals("vp")) {
					// unnecessary

				} else if (resource.contentEquals("f")) {
					// vertex / texture / normal
					String[] val = new String[3];
					for (int v = 0; v < 3; v++)
						val[v] = sc.next();

					int[] verts = new int[3];
					int[] texts = new int[3];
					int[] norms = new int[3];

					for (int a = 0; a < 3; a++) {
						verts[a] = Integer.valueOf(val[a].split("/")[0]);
						if (val[a].split("/")[1].isEmpty() == false)
							texts[a] = Integer.valueOf(val[a].split("/")[1]);
						norms[a] = Integer.valueOf(val[a].split("/")[2]);
					}

					m.faces.add(new Face(texts,
							new Vector[] { m.normals.get(norms[0] - 1), m.normals.get(norms[1] - 1),
									m.normals.get(norms[2] - 1) },
							new Point3D[] { m.vertices.get(verts[0] - 1), m.vertices.get(verts[1] - 1),
									m.vertices.get(verts[2] - 1) }));
					m.highest = highest;
					m.lowest = lowest;
					m.rightest = rightest;
					m.leftest = leftest;
					m.forwardest = forwardest;
					m.backest = backest;
					m.scale = scale;
				}
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return m;
	}
}
