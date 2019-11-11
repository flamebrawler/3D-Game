package components;

import Models.Model;
import Models.ModelLoader;
import framework.Vector;

public class WorldCreator {

	public static World create(int diameter,double MtnFqncy, double VlyFqncy,double scale, int waterLevel){
		World world = new World();
		Model gun = ModelLoader.Load("gatling-gun", 4);
		Model cube = ModelLoader.Load("cube", 5);
		Model sphere = ModelLoader.Load("sphere", 5);
		//world.objects.add(new Object(0, 0, 0, gun, false, false, true, new Vector(0, 0, 1)));
		//world.objects.add(new Object(sun.pos.getX(), sun.pos.getY(), sun.pos.getZ(), sphere, false, false, false,
		//		new Vector(0, 0, 1)));
		/*
		for (int z = -diameter/2; z < diameter/2; z += 50) {
			for (int x = -diameter/2; x < diameter/2; x += 50) {
				world.objects.add(new Object(x, 0, z, cube, true, true, false, new Vector(0, 0, 1)));
			}
		}
		*/
		int[][] tilemap = terrainMaker(diameter,MtnFqncy,VlyFqncy,scale);
		for (int z = -diameter/2; z < diameter/2; z += 50) {
			for (int x = -diameter/2; x < diameter/2; x += 50) {
				
				world.objects.add(new Object(x, tilemap[(z+diameter/2)/50][(x+diameter/2)/50], z, cube, true, true, false, new Vector(0, 0, 1)));
			}
		}
		world.objects.add(new Object(400, 50, 400, cube, true, true, false, new Vector(0, 0, 1)));
		return world;
	
	}
	static int[][] terrainMaker(int Diameter, double MtnFqncy, double VlyFqncy,double scale){
		int[][] tilemap = new int[Diameter/50][Diameter/50];
		for(int x = 0; x<Diameter/50;x++){
			for(int y = 0; y<Diameter/50;y++){
				double number= Math.random();
				//ambient smoothness
				if (number>MtnFqncy/2+.5){//adjust
					tilemap[x][y] = (int) (((int) (number*50)-25)*scale);
				}else if (number<VlyFqncy/2+.5){
					tilemap[x][y] = (int) (((int) (number*50)-25)*scale);
				}
				double percentage= Math.random()*100;
				// rare mountains / valleys
				if (percentage<0.5){//adjust
					tilemap[x][y] = (int) tilemap[x][y]+50;
				}else if (percentage>=99.5){
					tilemap[x][y] = (int) tilemap[x][y]-50;
				}
				
				
				
				
			}
		}
		return tilemap;
		
	}
}
